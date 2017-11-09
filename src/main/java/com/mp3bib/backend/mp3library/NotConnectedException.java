package com.mp3bib.backend.mp3library;

import java.io.IOException;

public class NotConnectedException extends IOException {
    private String context = "";
    public NotConnectedException(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return context + " not connected.";
    }
}
