import java.util.ArrayList;

public abstract class  Bindable {

    protected ArrayList<Bindable> bindables = new ArrayList<Bindable>();

    public void bind(Bindable counterpart) {
        if(!bindables.contains(counterpart)){
            bindables.add(counterpart);
            counterpart.bind(this);
        }
    }

    public void unbind(Bindable counterpart) {
        if(bindables.contains(counterpart)){
            bindables.remove(counterpart);
            counterpart.unbind(this);
        }
    }
}
