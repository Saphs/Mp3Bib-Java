package com.mp3bib.communication.command;

/**
 * @author Tizian Rettig - Saphs
 * @version 1.0.0
 */
public class sys_Kill extends Command{


    //TODO: revisit createSendable and buildInstance at a later date

    @Override
    public String invoke(String json) {
        backend.giveCloseRequest();
        return gson.toJson("Send kill request to backend.");
    }

    @Override
    public String createSendable() {
        return null;
    }
}
