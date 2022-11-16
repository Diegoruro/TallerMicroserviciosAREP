package edu.eci.arep.microserviceuser.service;

import edu.eci.arep.microservicepost.repository.MongoDbRepositoryPost;

import java.util.List;

public class UserService {
    private final MongoDbRepositoryPost mongoDbRepository;

    public UserService(MongoDbRepositoryPost mongoDbRepositoryUser) {
        this.mongoDbRepository = mongoDbRepositoryUser;
        this.mongoDbRepository.createConnection("userdb","users");
    }


    public List<User> getAllUsers(){
        return mongoDbRepository.getAllUsers();
    }
}
