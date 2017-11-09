package com.mp3bib.model;

import com.beaglebuddy.mp3.MP3;
import org.bson.Document;

/**
 * data type Class for common meta data
 * @author Daniel Liebler
 * @version 1.0.0
 */
public class CommonMetaData {
    private int internalDbID = -1;
    private String title = "";
    private String album = "";
    private String band = "";
    private int audioDuration = 0;
    private String musicType = "";


    public CommonMetaData(){}

    public CommonMetaData(int id, String title, String album, String band, int duration, String type){
        this.internalDbID = id;
        this.title = title;
        this.album = album;
        this.band = band;
        this.audioDuration = duration;
        this.musicType = type;
    }
    public CommonMetaData(int id, MP3 mp3) {
        this(id,
                mp3.getTitle(),
                mp3.getAlbum(),
                mp3.getBand(),
                mp3.getAudioDuration(),
                mp3.getMusicType());
    }

    public void appendToDocument(Document doc) {
        doc.append("id", this.internalDbID);
        doc.append("title", this.title);
        doc.append("album", this.album);
        doc.append("band", this.band);
        doc.append("audioDuration", this.audioDuration);
        doc.append("musicType", this.musicType);
    }

    /**
     * gets the Title of the Element
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets the Title of the Element
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets the Album of the Element
     * @return the Album name
     */
    public String getAlbum() {
        return album;
    }

    /**
     * sets the Album Name of the Element
     * @param album the Album name
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * gets the Band name of the Element
     * @return the Band name
     */
    public String getBand() {
        return band;
    }

    /**
     * sets the Band name of the Element
     * @param band the band name
     */
    public void setBand(String band) {
        this.band = band;
    }

    /**
     * gets the duration in seconds of the Element
     * @return the duration in seconds
     */
    public int getAudioDuration() {
        return audioDuration;
    }

    /**
     * sets the duration in seconds of the Element
     * @param audioDuration the duration in seconds
     */
    public void setAudioDuration(int audioDuration) {
        this.audioDuration = audioDuration;
    }

    /**
     * gets the Genre of the Element
     * @return the Genre
     */
    public String getMusicType() {
        return musicType;
    }

    /**
     * sets the Genre of the Element
     * @param musicType the Genre
     */
    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    /**
     * gets the id used by the database for the Element
     * @return the Title
     */
    public int getInternalDbID() {
        return internalDbID;
    }

    /**
     * sets the id used by the database for the Element
     * @param internalDbID the id used by the database
     */
    public void setInternalDbID(int internalDbID) {
        this.internalDbID = internalDbID;
    }
}
