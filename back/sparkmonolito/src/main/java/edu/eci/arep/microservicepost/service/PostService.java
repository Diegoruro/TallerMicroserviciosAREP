package edu.eci.arep.microservicepost.service;

import com.google.gson.JsonObject;
import edu.eci.arep.microservicepost.entity.Post;
import edu.eci.arep.microservicepost.repository.MongoDbRepositoryPost;

import java.util.ArrayList;
import java.util.List;

public class PostService{

    private final MongoDbRepositoryPost mongoDbRepository;

    public PostService(MongoDbRepositoryPost mongoDbRepository) {
        this.mongoDbRepository = mongoDbRepository;
        this.mongoDbRepository.createConnection("postdb","posts");
    }


    public void addPost(Post post){
        mongoDbRepository.addPost(post);
    }

    public List<String> getAllPost(){
        return mongoDbRepository.getAllDocuments();
    }
}
