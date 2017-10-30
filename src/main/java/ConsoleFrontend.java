import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleFrontend implements Runnable, BindableFrontend{

    private ArrayList<BindableBackend> boundBackends = new ArrayList<BindableBackend>();
    private Boolean answerPushed = false;
    private String lastResponseBody;

    public void bind(BindableBackend backend){
        boundBackends.add(backend);
    }

    // I/O operations //////////////////////////////////////
    @Override
    public synchronized void pushAnswer(String response) {
        processResponse(response);
        answerPushed = true;
        notify();
    }

    private void requestExecutionOf(String userCommandInput){
        for (BindableBackend backend: boundBackends) {
            System.out.println("<- " + userCommandInput);
            backend.pushRequest(userCommandInput);
            waitForExecution();
        }
    }
    ///////////////////////////////////////////////////////

    // Helper methods /////////////////////////////////////
    private synchronized void waitForExecution(){
        while (!answerPushed){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processResponse(String response) {
        lastResponseBody = response;
        System.out.println("-> " + response);
    }
    ///////////////////////////////////////////////////////


    @Override
    public void run() {
        System.out.println("Frontend:\t" + getClass().getTypeName() + " on " + Thread.currentThread().getName() + " starts.");

        Boolean closeRequest = false;
        while(!closeRequest){
            Scanner keyboardInput = new Scanner(System.in);
            System.out.print("# ");
            String enteredCommand = keyboardInput.next();
            if (enteredCommand.equals("exit")){
                closeRequest = true;
            }
            else requestExecutionOf(enteredCommand);
        }
        RequestExit();

        System.out.println("Frontend:\t" + getClass().getTypeName() + " on " + Thread.currentThread().getName() + " finished.");
    }

    private void RequestExit(){
        System.out.println("Unbinding frontend");
        for (BindableBackend backend :boundBackends) {
            backend.unbind(this);
        }

    }
}
