package com.mp3bib.communication;

/**
 * @author Tizian Rettig - Saphs
 * @version 1.0.0
 */
public abstract class BindableBackend extends Bindable {
    public abstract void pushRequest(String request);
    public abstract Boolean needsToClose();
    public abstract void killBackend();

    @Override
    public void unbind(Bindable counterpart) {
        super.unbind(counterpart);
        if(needsToClose()){
            killBackend();
        }
    }


}
