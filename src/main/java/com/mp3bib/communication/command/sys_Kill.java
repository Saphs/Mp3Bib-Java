package com.mp3bib.communication.command;

import com.mp3bib.backend.BackendprocessService;

/**
 * @author Tizian Rettig - Saphs
 * @version 1.0.0
 */
public class sys_Kill extends Command{


    //TODO: revisit createSendable and buildInstance at a later date

    @Override
    public String invoke(String json) {
        BackendprocessService currentBackend = BackendprocessService.getInstance();
        currentBackend.giveCloseRequest();
        return "Send kill request to backend.";
    }

    @Override
    public String createSendable(String... args) {
        return null;
    }

    @Override
    public Command buildInstance(String jsonSendable) {
        return null;
    }
}
