public abstract class BindableBackend extends Bindable {
    abstract void pushRequest(String request);
    abstract Boolean needsToClose();
    abstract void killBackend();

    @Override
    public void unbind(Bindable counterpart) {
        super.unbind(counterpart);
        if(needsToClose()){
            killBackend();
        }
    }


}
