package com.mp3bib.backend;

import com.mp3bib.communication.Bindable;
import com.mp3bib.communication.BindableFrontend;

import java.util.ArrayList;

/**
 * @author Tizian Rettig - Saphs
 * @version 1.0.0
 */
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
