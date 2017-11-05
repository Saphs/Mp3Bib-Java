

import com.mp3bib.logging.CustomLogger;
import com.mp3bib.logging.Logger;
import java.util.ArrayList;


/**
 * This class is implemented as an singelton, use the getInstance() instead of a normal constructor call.
 */
public class BackendprocessService implements Runnable, BindableBackend{

    public Logger logger = new CustomLogger(Logger.LOGLEVEL_INFO);

    private ArrayList<String> requestBuffer = new ArrayList<>();
    private CommandExecuter commandExecuter = new CommandExecuter();
    private ResponseDistributer responseDistributer = new ResponseDistributer();

    private Boolean closeRequest = false;

    private static final BackendprocessService instance = new BackendprocessService();

    private BackendprocessService() {
        logger.debug("New Object of " + getClass().getName() + "instantiated.");
    }

    public static BackendprocessService getInstance() {
        return instance;
    }

    @Override
    public void run() {
        logger.info("Backend:\t" + getClass().getTypeName() + " on " + Thread.currentThread().getName() + " starts.");

        synchronized (this) {

            while (!closeRequest) {
                while (requestBuffer.isEmpty()) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!closeRequest) { executeRequestedCommand(); }
            }
        }

        logger.info("Backend:\t" + getClass().getTypeName() + " on " + Thread.currentThread().getName() + " finished.");
    }

    private void executeRequestedCommand(){
        String currentRequest = requestBuffer.get(0);
        if (CommandValidator.validateCommand(currentRequest)){

            String response = "Empty Response";
            try{
                if (currentRequest.startsWith("$")) response = callSystemCommand(currentRequest);
                else response = callCommand(currentRequest);
            }catch(Exception e){
                logger.error(e.getMessage());
            }
            responseDistributer.answerAny(response);
        }
        requestBuffer.remove(0);
    }

    private String callSystemCommand(String systemCommand) {
        String commandResult;
        switch ( systemCommand ) {
            case "$NoBF":
                commandResult = String.valueOf(responseDistributer.numberOfBoundFrontends());
                break;

            case "$Kill":
                this.closeRequest = true;
                commandResult = "shutdown requested";
                break;

            default:
                commandResult = "empty command result";
                break;
        }
        return commandResult;
    }

    private String callCommand(String command){
        switch( command ){
            case "command1": return commandExecuter.getMusicList();
            default: throw new IllegalArgumentException("Command " + command + " was not found in " + getClass().getName());
        }
    }


    @Override
    public synchronized void pushRequest(String request) {
        requestBuffer.add(request);
        notify();
    }

    public void bind(BindableFrontend frontend){
        responseDistributer.bindFrontend(frontend);
    }

    public void unbind(BindableFrontend frontend){
        responseDistributer.unbindFrontend(frontend);
        if (responseDistributer.numberOfBoundFrontends() <= 0){
            logger.info("Backend left without binding - exiting");
            pushRequest("$Kill");
        }
    }


}
