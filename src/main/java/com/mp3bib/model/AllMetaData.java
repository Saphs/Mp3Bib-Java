package com.mp3bib.model;

import com.beaglebuddy.id3.enums.ID3TagVersion;
import com.beaglebuddy.mp3.MP3;
import com.beaglebuddy.mpeg.enums.ChannelMode;
import com.beaglebuddy.mpeg.enums.Layer;
import com.beaglebuddy.mpeg.enums.MPEGVersion;
import org.bson.Document;

public class AllMetaData extends DetailedMetaData {
    private int audioSize = 0;
    private String lyrics = "";
    private String publisher = "";
    private MPEGVersion mpegVersion = null;
    private ChannelMode channelMode = null;
    private Layer layer = null;
    private ID3TagVersion tagVersion = null;

    public AllMetaData() {}

    public AllMetaData(DetailedMetaData detailed,
                       int audioSize,
                       String lyrics,
                       String publisher,
                       MPEGVersion mpegVersion,
                       ChannelMode channelMode,
                       Layer layer,
                       ID3TagVersion tagVersion){
        super(detailed);
        this.audioSize = audioSize;
        this.lyrics = lyrics;
        this.publisher = publisher;
        this.mpegVersion = mpegVersion;
        this.channelMode = channelMode;
        this.layer = layer;
        this.tagVersion = tagVersion;
    }

    public AllMetaData(int id, MP3 mp3) {
        this(new DetailedMetaData(id, mp3),
                mp3.getAudioSize(),
                mp3.getLyrics(),
                mp3.getPublisher(),
                mp3.getMPEGVersion(),
                mp3.getChannelMode(),
                mp3.getLayer(),
                mp3.getVersion());
    }

    public void appendToDocument(Document doc) {
        super.appendToDocument(doc);
        doc.append("audioSize", this.audioSize);
        doc.append("lyrics", this.lyrics);
        doc.append("publisher", this.publisher);
        doc.append("mpegVersion", this.mpegVersion);
        doc.append("channelMode", this.channelMode);
        doc.append("layer", this.layer);
        doc.append("tagVersion", this.tagVersion);
    }
    public static DetailedMetaData fromDocument(AllMetaData allMeta, Document doc) {
        DetailedMetaData.fromDocument(allMeta, doc);

        allMeta.setAudioSize((int) doc.get("audioSize"));
        allMeta.setLyrics((String) doc.get("lyrics"));
        allMeta.setPublisher((String) doc.get("publisher"));
        allMeta.setMpegVersion((MPEGVersion) doc.get("mpegVersion"));
        allMeta.setChannelMode((ChannelMode) doc.get("channelMode"));
        allMeta.setLayer((Layer) doc.get("layer"));
        allMeta.setTagVersion((ID3TagVersion) doc.get("tagVersion"));

        return allMeta;
    }


    /**
     * gets audioSize
     * @return audioSize
     */
    public int getAudioSize() {
        return audioSize;
    }

    /**
     * sets audioSize
     * @param audioSize audioSize
     */
    public void setAudioSize(int audioSize) {
        this.audioSize = audioSize;
    }

    /**
     * gets lyrics
     * @return lyrics
     */
    public String getLyrics() {
        return lyrics;
    }

    /**
     * sets lyrics
     * @param lyrics lyrics
     */
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    /**
     * gets publisher
     * @return publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * sets publisher
     * @param publisher publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * gets mpegVersion
     * @return mpegVersion
     */
    public MPEGVersion getMpegVersion() {
        return mpegVersion;
    }

    /**
     * sets mpegVersion
     * @param mpegVersion mpegVersion
     */
    public void setMpegVersion(MPEGVersion mpegVersion) {
        this.mpegVersion = mpegVersion;
    }

    /**
     * gets channelMode
     * @return channelMode
     */
    public ChannelMode getChannelMode() {
        return channelMode;
    }

    /**
     * sets channelMode
     * @param channelMode channelMode
     */
    public void setChannelMode(ChannelMode channelMode) {
        this.channelMode = channelMode;
    }

    /**
     * gets layer
     * @return layer
     */
    public Layer getLayer() {
        return layer;
    }

    /**
     * sets layer
     * @param layer layer
     */
    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    /**
     * gets tagVersion
     * @return tagVersion
     */
    public ID3TagVersion getTagVersion() {
        return tagVersion;
    }

    /**
     * sets tagVersion
     * @param tagVersion tagVersion
     */
    public void setTagVersion(ID3TagVersion tagVersion) {
        this.tagVersion = tagVersion;
    }
}
