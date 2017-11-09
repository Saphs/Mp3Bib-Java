package com.mp3bib.communication;

import java.util.ArrayList;


/**
 * Provides structural definition for any bindable object
 * that can be called or accessed externally.
 *
 * @author Tizian Rettig - Saphs
 * @version 1.0.0
 */
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
