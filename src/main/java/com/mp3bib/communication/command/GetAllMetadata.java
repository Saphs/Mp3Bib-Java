package com.mp3bib.communication.command;

import com.mp3bib.backend.BackendprocessService;
import com.mp3bib.backend.mp3library.Mp3IO;
import com.mp3bib.backend.mp3library.NotConnectedException;
import com.mp3bib.model.AllMetaData;
import com.mp3bib.model.DetailedMetaData;

import java.util.ArrayList;

/**
 * queries the detailed Metadata for a given id
 * @author Daniel Liebler
 * @version 1.0.0
 */
public class GetAllMetadata extends Command{
    private int id;


    public GetAllMetadata createObject(String param) throws IllegalArgumentException {
        ArrayList<String> args = seperateArgs(param);
        try {
            return new GetAllMetadata(Integer.parseInt(args.get(0)));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
    public GetAllMetadata(int id){
        this.id = id;
    }
    public GetAllMetadata(){
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
            GetAllMetadata cmd = BackendprocessService.gson.fromJson(json, GetAllMetadata.class);

            String path = BackendprocessService.getInstance().database.getFilePathByID(cmd.getID());
            AllMetaData metadata = new AllMetaData(cmd.getID(), Mp3IO.getMP3Tags(path));
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
