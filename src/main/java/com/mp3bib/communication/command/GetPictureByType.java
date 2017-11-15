package com.mp3bib.communication.command;

import com.beaglebuddy.id3.enums.PictureType;
import com.beaglebuddy.id3.pojo.AttachedPicture;
import com.mp3bib.backend.BackendprocessService;
import com.mp3bib.backend.mp3library.Mp3IO;
import com.mp3bib.backend.mp3library.NotConnectedException;

import java.util.ArrayList;

/**
 * queries the Picture for a given id
 * @author Daniel Liebler
 * @version 1.0.0
 */
public class GetPictureByType extends Command{
    private int id;
    private String type;

    public GetPictureByType createObject(String param) throws IllegalArgumentException {
        ArrayList<String> args = seperateArgs(param);
        try {
            return new GetPictureByType(Integer.parseInt(args.get(0)), args.get(1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
    public GetPictureByType(int id, String type){
        this.id = id;
        this.type = type;
    }
    public GetPictureByType(){
        this.id = -1;
        this.type = PictureType.FRONT_COVER.toString();
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String invoke(String json) {
        GetPictureByType cmd = BackendprocessService.gson.fromJson(json, GetPictureByType.class);
        String myType = cmd.getType();
        try {
            String path = BackendprocessService.getInstance().database.getFilePathByID(cmd.getID());
            BackendprocessService.getInstance().logger.info("Path is " + path);
            BackendprocessService.getInstance().logger.info("Pictures are " + PictureType.valueOf(myType).toString() + "/" + Mp3IO.getMP3Tags(path).getPictures().size() + "/" + Mp3IO.getMP3Tags(path).getPictures().get(0).getPictureType().toString());
            AttachedPicture picture = Mp3IO.getMP3Tags(path).getPicture(PictureType.valueOf(myType));
            BackendprocessService.getInstance().logger.info("Picture is " + picture.toString());

            String txt = BackendprocessService.gson.toJson(picture.getImage());
            BackendprocessService.getInstance().logger.info(picture.toString() + " encoded Picture: " + txt);
            return txt;
        } catch (NotConnectedException e) {
            BackendprocessService.getInstance().logger.error(e.toString());
            return "";
        } catch (IllegalArgumentException e) {
            BackendprocessService.getInstance().logger.error(e.toString() + " Type was " + myType);
            return "";
        } catch (NullPointerException e) {
            BackendprocessService.getInstance().logger.error(e.toString() + " Type was " + myType);
            return "";
        }
    }

    @Override
    public String createSendable() {
        return BackendprocessService.gson.toJson(this);
    }
}
