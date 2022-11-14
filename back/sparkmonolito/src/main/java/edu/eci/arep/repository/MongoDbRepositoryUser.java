package edu.eci.arep.repository;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.eci.arep.entity.User;
import org.bson.Document;

import java.util.ArrayList;
import java.util.function.Consumer;


public class MongoDbRepositoryUser {

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


    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        FindIterable<Document> result = this.mongoCollection.find();
        System.out.println(result);
        result.forEach((Consumer<? super Document>) document -> {
            User user = new User(document.getString("username"),document.getString("password"));
            users.add(user);
        });
        return users;
    }
}
