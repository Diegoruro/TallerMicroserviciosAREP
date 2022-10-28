package edu.eci.arep.repository;

import com.mongodb.MongoException;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.eci.arep.entity.Post;
import org.bson.Document;

import java.util.ArrayList;
import java.util.function.Consumer;


public class MongoDbRepository {

    private String url = "localhost";
    private int port = 27017;
    private MongoClient mongoClient = null;
    private MongoDatabase mongoDatabase = null;
    private MongoCollection<Document> mongoCollection;


    public void createConnection(String dataBaseName, String collection) {
        try {
            this.mongoClient = new MongoClient(this.url);
            this.mongoDatabase = this.mongoClient.getDatabase(dataBaseName);
            this.mongoCollection = this.mongoDatabase.getCollection(collection);
        } catch (MongoException ex){
            System.out.println(ex.toString());
        }
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

    public ArrayList<Post> getAllDocuments() {
        ArrayList<Post> posts = new ArrayList<>();

        FindIterable<Document> result = this.mongoCollection.find();
        System.out.println(result);
        result.forEach((Consumer<? super Document>) document -> {
            Post post = new Post(document.getString("text"),document.getString("userName"),document.getString("date"));
            posts.add(post);
        });
        return posts;
    }
}
