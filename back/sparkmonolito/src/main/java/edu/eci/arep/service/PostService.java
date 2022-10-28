package edu.eci.arep.service;

import edu.eci.arep.entity.Post;
import edu.eci.arep.repository.MongoDbRepository;

import java.util.List;

public class PostService{

    private final MongoDbRepository mongoDbRepository;

    public PostService(MongoDbRepository mongoDbRepository) {
        this.mongoDbRepository = mongoDbRepository;
        this.mongoDbRepository.createConnection("postdb","posts");
    }


    public void addPost(Post post){
        mongoDbRepository.addPost(post);
    }

    public List<Post> getAllPost(){
        return mongoDbRepository.getAllDocuments();
    }
}
