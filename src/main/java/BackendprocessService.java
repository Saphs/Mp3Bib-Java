

import java.util.ArrayList;


/**
 * This class is implemented as an singelton, use the getInstance() instead of a normal constructor call.
 */
public class BackendprocessService extends BindableBackend implements Runnable{

    private ArrayList<String> requestBuffer = new ArrayList<>();
    private CommandExecuter commandExecuter = new CommandExecuter();
    private ResponseDistributer responseDistributer = new ResponseDistributer(super.bindables);

    private Boolean closeRequest = false;

    // Singelton implementation ----------------------------------------------------------------------------------------
    private static final BackendprocessService instance = new BackendprocessService();

    private BackendprocessService() {
        System.out.println("New Object of " + getClass().getName() + "instantiated.");
    }

    public static BackendprocessService getInstance() {
        return instance;
    }
    //------------------------------------------------------------------------------------------------------------------


    // Implementation ----------------------------------------------------------------------------------------------------
    @Override
    public void run() {
        System.out.println("Backend:\t" + getClass().getTypeName() + " on " + Thread.currentThread().getName() + " starts.");
        synchronized (this) {
            while (!closeRequest) {
                waitForRequest();
                if (!closeRequest) { executeRequestedCommand(); }
            }
        }
        System.out.println("Backend:\t" + getClass().getTypeName() + " on " + Thread.currentThread().getName() + " finished.");
    }

    @Override
    public synchronized void pushRequest(String request) {
        requestBuffer.add(request);
        notify();
    }

    @Override
    Boolean needsToClose() {
        return super.bindables.isEmpty();
    }

    @Override
    void killBackend() {
        pushRequest("$Kill");
    }
    //------------------------------------------------------------------------------------------------------------------

    // Helper methods --------------------------------------------------------------------------------------------------
    private void executeRequestedCommand(){
        String currentRequest = requestBuffer.get(0);
        String response = "";

        if (CommandValidator.validateCommand(currentRequest)){
            try{
                if (currentRequest.startsWith("$")) response = callSystemCommand(currentRequest);
                else response = callCommand(currentRequest);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        else response = "Invalid Command.";

        responseDistributer.answerAny(response);
        requestBuffer.remove(0);
    }

    private void waitForRequest(){
        while (requestBuffer.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------

    // Command calls ---------------------------------------------------------------------------------------------------
    private String callSystemCommand(String systemCommand) {
        String commandResult;
        switch ( systemCommand ) {
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
    //------------------------------------------------------------------------------------------------------------------
}