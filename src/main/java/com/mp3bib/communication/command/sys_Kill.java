package com.mp3bib.communication.command;


import com.mp3bib.backend.BackendprocessService;

/**
 * @author Tizian Rettig - Saphs
 * @version 1.0.0
 */
public class sys_Kill extends Command{

    //TODO: revisit createSendable and buildInstance at a later date

    public sys_Kill() {}
    public sys_Kill createObject(String param) throws IllegalArgumentException {
        return new sys_Kill();
    }

    @Override
    public String invoke(String json) {
        BackendprocessService.getInstance().giveCloseRequest();
        return BackendprocessService.gson.toJson("Send kill request to backend.");
    }

    @Override
    public String createSendable() {
        return BackendprocessService.gson.toJson(this);
    }
}
