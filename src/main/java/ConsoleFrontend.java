import java.util.Scanner;

public class ConsoleFrontend extends BindableFrontend implements Runnable{

    private String responseBuffer = "";

    // Implementation --------------------------------------------------------------------------------------------------
    @Override
    public void run() {
        System.out.println("Frontend:\t" + getClass().getTypeName() + " on " + Thread.currentThread().getName() + " starts.");

        Scanner keyboardInput = new Scanner(System.in);
        Boolean closeRequest = false;
        while(!closeRequest){

            System.out.print("# ");
            String enteredCommand = keyboardInput.next();
            if (enteredCommand.equals("exit")) {
                closeRequest = true;
            }
            else {
                requestExecutionOf(enteredCommand);
                waitForExecution();
                printResponse();
            }
        }
        RequestExit();

        System.out.println("Frontend:\t" + getClass().getTypeName() + " on " + Thread.currentThread().getName() + " finished.");
    }

    @Override
    public synchronized void pushAnswer(String response) {
        responseBuffer = response;
        notify();
    }
    //------------------------------------------------------------------------------------------------------------------

    // Helper methods --------------------------------------------------------------------------------------------------
    private void requestExecutionOf(String userCommandInput){
        for (Bindable element : bindables) {
            if (element instanceof BindableBackend){
                System.out.println("<- " + userCommandInput);
                ((BindableBackend) element).pushRequest(userCommandInput);
            }
        }
    }


    private synchronized void waitForExecution(){
        while (responseBuffer.equals("")){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printResponse() {
        System.out.println("-> " + responseBuffer);
        responseBuffer = "";
    }

    private void RequestExit(){
        System.out.println("Unbinding frontend");
        for (int i = 0; i < bindables.size(); i++) {
            unbind(bindables.get(i));
        }
    }
    //------------------------------------------------------------------------------------------------------------------
}
