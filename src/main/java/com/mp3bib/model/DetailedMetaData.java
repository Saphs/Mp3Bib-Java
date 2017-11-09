package com.mp3bib.model;

import com.beaglebuddy.mp3.MP3;
import com.beaglebuddy.mpeg.enums.BitrateType;
import org.bson.Document;

public class DetailedMetaData extends CommonMetaData {
    BitrateType bitrateType = null;
    String codec = "";
    String comments = "";
    String leadPerformer = "";
    String lyricsBy = "";
    String musicBy = "";
    int bitrate = 0;
    int frequency = 0;
    int rating = 0;
    int track = 0;
    int year = 0;

    public DetailedMetaData(){}

    public DetailedMetaData(DetailedMetaData detailed) {
        super(detailed);
        this.bitrate = detailed.bitrate;
        this.bitrateType = detailed.bitrateType;
        this.codec = detailed.codec;
        this.comments = detailed.comments;
        this.frequency = detailed.frequency;
        this.leadPerformer = detailed.leadPerformer;
        this.lyricsBy = detailed.lyricsBy;
        this.musicBy = detailed.musicBy;
        this.rating = detailed.rating;
        this.track = detailed.track;
        this.year = detailed.year;
    }

    public DetailedMetaData(CommonMetaData common,
                            int bitrate,
                            BitrateType bitrateType,
                            String codec,
                            String comments,
                            int frequency,
                            String leadPerformer,
                            String lyricsBy,
                            String musicBy,
                            int rating,
                            int track,
                            int year){
        super(common);
        this.bitrate = bitrate;
        this.bitrateType = bitrateType;
        this.codec = codec;
        this.comments = comments;
        this.frequency = frequency;
        this.leadPerformer = leadPerformer;
        this.lyricsBy = lyricsBy;
        this.musicBy = musicBy;
        this.rating = rating;
        this.track = track;
        this.year = year;
    }

    public DetailedMetaData(int id, MP3 mp3) {
        this(new CommonMetaData(id, mp3),
                mp3.getBitrate(),
                mp3.getBitrateType(),
                mp3.getCodec(),
                mp3.getComments(),
                mp3.getFrequency(),
                mp3.getLeadPerformer(),
                mp3.getLyricsBy(),
                mp3.getMusicBy(),
                mp3.getRating(),
                mp3.getTrack(),
                mp3.getYear());
    }

    public void appendToDocument(Document doc) {
        super.appendToDocument(doc);
        doc.append("bitrate", this.bitrate);
        doc.append("bitrateType", this.bitrateType);
        doc.append("codec", this.codec);
        doc.append("comments", this.comments);
        doc.append("frequency", this.frequency);
        doc.append("leadPerformer", this.leadPerformer);
        doc.append("lyricsBy", this.lyricsBy);
        doc.append("musicBy", this.musicBy);
        doc.append("rating", this.rating);
        doc.append("track", this.track);
        doc.append("year", this.year);
    }

    public static DetailedMetaData fromDocument(DetailedMetaData detailedMeta, Document doc) {
        CommonMetaData.fromDocument(detailedMeta, doc);

        detailedMeta.setBitrate((int) doc.get("bitrate"));
        detailedMeta.setBitrateType((BitrateType) doc.get("bitrateType"));
        detailedMeta.setCodec((String) doc.get("codec"));
        detailedMeta.setComments((String) doc.get("comments"));
        detailedMeta.setFrequency((int) doc.get("frequency"));
        detailedMeta.setLeadPerformer((String) doc.get("leadPerformer"));
        detailedMeta.setLyricsBy((String) doc.get("lyricsBy"));
        detailedMeta.setMusicBy((String) doc.get("musicBy"));
        detailedMeta.setRating((int) doc.get("rating"));
        detailedMeta.setTrack((int) doc.get("track"));
        detailedMeta.setYear((int) doc.get("year"));
        return detailedMeta;
    }

    /**
     * gets bitrateType
     * @return bitrateType
     */
    public BitrateType getBitrateType() {
        return bitrateType;
    }

    /**
     * sets bitrateType
     * @param bitrateType bitrateType
     */
    public void setBitrateType(BitrateType bitrateType) {
        this.bitrateType = bitrateType;
    }

    /**
     * gets codec
     * @return codec
     */
    public String getCodec() {
        return codec;
    }

    /**
     * sets codec
     * @param codec codec
     */
    public void setCodec(String codec) {
        this.codec = codec;
    }

    /**
     * gets comments
     * @return comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * sets comments
     * @param comments comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * gets leadPerformer
     * @return leadPerformer
     */
    public String getLeadPerformer() {
        return leadPerformer;
    }

    /**
     * sets leadPerformer
     * @param leadPerformer leadPerformer
     */
    public void setLeadPerformer(String leadPerformer) {
        this.leadPerformer = leadPerformer;
    }

    /**
     * gets lyricsBy
     * @return lyricsBy
     */
    public String getLyricsBy() {
        return lyricsBy;
    }

    /**
     * sets lyricsBy
     * @param lyricsBy lyricsBy
     */
    public void setLyricsBy(String lyricsBy) {
        this.lyricsBy = lyricsBy;
    }

    /**
     * gets musicBy
     * @return musicBy
     */
    public String getMusicBy() {
        return musicBy;
    }

    /**
     * sets musicBy
     * @param musicBy musicBy
     */
    public void setMusicBy(String musicBy) {
        this.musicBy = musicBy;
    }

    /**
     * gets bitrate
     * @return bitrate
     */
    public int getBitrate() {
        return bitrate;
    }

    /**
     * sets bitrate
     * @param bitrate bitrate
     */
    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    /**
     * gets frequency
     * @return frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * sets frequency
     * @param frequency frequency
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /**
     * gets rating
     * @return rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * sets rating
     * @param rating rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * gets track
     * @return track
     */
    public int getTrack() {
        return track;
    }

    /**
     * sets track
     * @param track track
     */
    public void setTrack(int track) {
        this.track = track;
    }

    /**
     * gets year
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * sets year
     * @param year year
     */
    public void setYear(int year) {
        this.year = year;
    }
}
