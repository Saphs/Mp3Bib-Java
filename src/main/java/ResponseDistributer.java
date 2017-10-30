import java.util.ArrayList;

public class ResponseDistributer {

    private ArrayList<BindableFrontend> boundFrontends = new ArrayList<>();

    public void answerAny(String response){
        for (BindableFrontend frontend : boundFrontends) {
            frontend.pushAnswer(response);
        }
    }

    public void bindFrontend (BindableFrontend frontend){
        boundFrontends.add(frontend);
    }

    public void unbindFrontend (BindableFrontend frontend){
        boundFrontends.remove(frontend);
    }

    public int numberOfBoundFrontends(){
        return boundFrontends.size();
    }

}
