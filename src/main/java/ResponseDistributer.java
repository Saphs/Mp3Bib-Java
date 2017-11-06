import java.util.ArrayList;

public class ResponseDistributer {

    private ArrayList<Bindable> boundElements;

    ResponseDistributer(ArrayList<Bindable> newBoundElements){
        this.boundElements = newBoundElements;
    }

    public void answerAny(String response){
        for (Bindable element : boundElements) {
            if (element instanceof BindableFrontend){
                ((BindableFrontend) element).pushAnswer(response);
            }
        }
    }

}
