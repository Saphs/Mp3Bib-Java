package com.mp3bib.communication.command;

import com.mp3bib.backend.BackendprocessService;
import com.mp3bib.backend.mp3library.NotConnectedException;
import com.mp3bib.model.CommonMetaData;

/**
 * @author Tizian Rettig - Saphs
 * @author Daniel Liebler
 * @version 1.0.0
 */
public class GetMusicList extends Command{

    public GetMusicList createObject(String param) throws IllegalArgumentException {
        return new GetMusicList();
    }

    public GetMusicList(){}

    @Override
    public String invoke(String json) {
        try {
            CommonMetaData[] list = BackendprocessService.getInstance().database.getAll();
            String txt = BackendprocessService.gson.toJson(list);
            return txt;
        } catch (NotConnectedException e) {
            BackendprocessService.getInstance().logger.error(e.toString());
            return "";
        }
    }

    @Override
    public String createSendable() {
        return BackendprocessService.gson.toJson(this);
    }


}
