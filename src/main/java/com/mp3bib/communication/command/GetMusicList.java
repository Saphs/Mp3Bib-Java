package com.mp3bib.communication.command;

import com.mp3bib.model.CommonMetaData;

/**
 * @author Tizian Rettig - Saphs
 * @author Daniel Liebler
 * @version 1.0.0
 */
public class GetMusicList extends Command{

    public GetMusicList(){}

    @Override
    public String invoke(String json) {
        CommonMetaData[] list = null;
        //TODO: implement getMusicList in DB connector
        // list = getMusicList();
        String txt = gson.toJson(list);
        return txt;
    }

    @Override
    public String createSendable() {
        return null;
    }


}
