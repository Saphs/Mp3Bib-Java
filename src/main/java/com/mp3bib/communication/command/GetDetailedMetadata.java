package com.mp3bib.communication.command;

import com.mp3bib.backend.BackendprocessService;
import com.mp3bib.backend.mp3library.NotConnectedException;
import com.mp3bib.model.DetailedMetaData;

import java.util.ArrayList;

/**
 * queries the detailed Metadata for a given id
 * @author Daniel Liebler
 * @version 1.0.0
 */
public class GetDetailedMetadata extends Command{
    private int id;


    public GetDetailedMetadata createObject(String param) throws IllegalArgumentException {
        ArrayList<String> args = seperateArgs(param);
        try {
            return new GetDetailedMetadata(Integer.parseInt(args.get(0)));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
    public GetDetailedMetadata(int id){
        this.id = id;
    }
    public GetDetailedMetadata(){
        this.id = -1;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String invoke(String json) {
        try {
            GetDetailedMetadata cmd = BackendprocessService.gson.fromJson(json, GetDetailedMetadata.class);

            DetailedMetaData metadata = BackendprocessService.getInstance().database.getById(cmd.getID());
            String txt = BackendprocessService.gson.toJson(metadata);
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
