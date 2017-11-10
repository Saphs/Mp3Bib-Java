package com.mp3bib.frontend;

import com.mp3bib.communication.Bindable;
import com.mp3bib.communication.BindableBackend;
import com.mp3bib.communication.BindableFrontend;

import java.util.ArrayList;

/**
 * @author Tizian Rettig - Saphs
 * @version 1.0.0
 */
public class TestingFrontend extends BindableFrontend{

    private ArrayList<String> responses = new ArrayList<>();

    @Override
    public synchronized void pushAnswer(String response) {
        responses.add(response);
        notify();
    }

    public ArrayList<String> getResponses() {
        return responses;
    }

    public String lastResponse(){
        return responses.get(responses.size()-1);
    }

    public int clear(){
        int size = responses.size();
        responses.clear();
        return size;
    }

    public synchronized void callBackend(String request) {
        for (Bindable element : bindables) {
            if (element instanceof BindableBackend){
                ((BindableBackend) element).pushRequest(request);
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
