package edu.eci.arep.microservicepost.repository;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import edu.eci.arep.microservicepost.entity.Post;
import org.bson.Document;

import javax.json.Json;
import java.util.ArrayList;
import java.util.function.Consumer;


public class MongoDbRepositoryPost {

    private MongoClient mongoClient = null;
    private MongoDatabase mongoDatabase = null;
    private MongoCollection<Document> mongoCollection;

    public MongoDatabase createConnection(String dataBaseName, String collection){
        ConnectionString connectionString = new ConnectionString("mongodb://pacho:pacho@ac-dwefend-shard-00-00.mblvif0.mongodb.net:27017,ac-dwefend-shard-00-01.mblvif0.mongodb.net:27017,ac-dwefend-shard-00-02.mblvif0.mongodb.net:27017/?ssl=true&replicaSet=atlas-wk6jcp-shard-0&authSource=admin&retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        mongoDatabase = mongoClient.getDatabase(dataBaseName);
        mongoCollection = mongoDatabase.getCollection(collection);
        return mongoDatabase;
    }

    public void closeConnection() {
        this.mongoClient.close();
    }

    public void addPost(Post post){
        Document document = new Document();
        document.put("text",post.getText());
        document.put("date",post.getDate());
        document.put("userName",post.getUserName());

        this.mongoCollection.insertOne(document);

    }

    public ArrayList<String> getAllDocuments() {
        ArrayList<String> posts = new ArrayList<>();

        FindIterable<Document> result = this.mongoCollection.find();
        result.forEach((Consumer<? super Document>) document -> {
            String json = "{ \"text\":" + document.getString("text") + ",\"userName\":" + document.getString("userName") + ",\"date\":" + document.getString("date") + "}";
            System.out.println(json);
            posts.add(json);
        });
        return posts;
    }

}
