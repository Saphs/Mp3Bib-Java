package com.mp3bib.backend.mp3library;

import com.beaglebuddy.mp3.MP3;
import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import com.mp3bib.Configuration;
import com.mp3bib.backend.BackendprocessService;
import com.mp3bib.model.CommonMetaData;
import com.mp3bib.model.DetailedMetaData;
import org.bson.Document;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;

public class Database {

    private final Gson gson = new Gson();

    private MongoClient client;
    private MongoDatabase musicDB;
    private MongoCollection<Document> collection;

    private String host = "localhost";
    private int port = 27017;
    private String databaseName = "musicDB";
    private String collectionName = "musicDB";

    public Database() {
        try {
            host = Configuration.getConfigAsString("HOST");
            port = Configuration.getConfigAsInt("PORT");
            databaseName = Configuration.getConfigAsString("DATABASE_NAME");
            collectionName = Configuration.getConfigAsString("COLLECTION_NAME");
        } catch (IOException e) {
            BackendprocessService.getInstance().logger.error(e.toString());
        }
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
    public int addEntry(MP3 mp3) throws NotConnectedException {
        Document doc = createDocumentFromMp3(mp3);
        this.getCollection().insertOne(doc);
        return (int) doc.get("id");
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
        return gson.fromJson(getCollection().find(eq("id", id)).first().toJson(), DetailedMetaData.class);
    }

    /**
     * gets the Filepath for the id
     * @param id the queried id
     * @return the DetailedMetaData
     */
    public String getFilePathByID(int id) throws NotConnectedException {
        return (String) getCollection().find(eq("id", id)).first().get("path");
    }

    public CommonMetaData[] getAll() throws NotConnectedException {
        CommonMetaData[] commonList = new CommonMetaData[(int) this.getCount()];
        Block<Document> listBlock = new Block<Document>() {
            private int i = 0;
            @Override
            public void apply(final Document document) {
                commonList[i] = gson.fromJson(document.toJson(), CommonMetaData.class);
                i++;
            }
        };
        getCollection().find().forEach(listBlock);
        return commonList;
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
        DetailedMetaData metaData = new DetailedMetaData(id, mp3);
        Document doc = Document.parse(gson.toJson(metaData));
        doc.append("path", mp3.getPath());
        return doc;
    }
}
