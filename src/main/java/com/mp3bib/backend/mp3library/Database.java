package com.mp3bib.backend.mp3library;

import com.beaglebuddy.mp3.MP3;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import com.mp3bib.model.DetailedMetaData;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class Database {

    private MongoClient client;
    private MongoDatabase musicDB;
    private MongoCollection<Document> collection;

    private String host = "localhost";
    private int port = 27017;
    private String databaseName = "musicDB";
    private String collectionName = "musicDB";

    public Database() {
        this.connectToDB();
    }

    public Database(String host, int port, String databaseName, String collectionName) {
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.collectionName = collectionName;
        this.connectToDB();
    }

    /**
     * adds an entry to the DB
     * @param mp3 a MP3 Object for the entry
     * @throws NotConnectedException
     */
    public void addEntry(MP3 mp3) throws NotConnectedException {
        this.getCollection().insertOne(createDocumentFromMp3(mp3));
    }

    /**
     * query the number of entrys for mp3s in DB
     * @return the number of entrys
     * @throws NotConnectedException
     */
    public long getCount() throws NotConnectedException {
        return getCollection().count();
    }

    /**
     * get the DetailedMetaData for the id
     * @param id the queried id
     * @return the DetailedMetaData
     */
    public DetailedMetaData getById(int id) throws NotConnectedException {
        return DetailedMetaData.fromDocument(getCollection().find(eq("id", id)).first());
    }

    /**
     * gets the Filepath for the id
     * @param id the queried id
     * @return the DetailedMetaData
     */
    public String getFilePathByID(int id) throws NotConnectedException {
        return (String) getCollection().find(eq("id", id)).first().get("path");
    }




    //------------------------------------------------------------------------------------------------------------------


    private MongoCollection<Document> getCollection() throws NotConnectedException {
        if (this.collection == null) {
            throw new NotConnectedException("MongoDB Collection");
        }
        return this.collection;
    }

    private void connectToDB() {
        this.client = new MongoClient(host , port);
        this.musicDB = this.client.getDatabase(databaseName);
        this.collection = musicDB.getCollection(collectionName);
    }

    private int getLargestID() throws NotConnectedException {
        return (int) getCollection().find().sort(Sorts.descending("id")).limit(1).first().get("id");
    }

    private Document createDocumentFromMp3(MP3 mp3) throws NotConnectedException {
        int id = getLargestID() + 1;
        Document doc = new Document("path", mp3.getPath());
        DetailedMetaData metaData = new DetailedMetaData(id, mp3);
        metaData.appendToDocument(doc);
        return doc;
    }
}
