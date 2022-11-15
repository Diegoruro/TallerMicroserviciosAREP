package edu.eci.arep.microserviceuser.service;

import edu.eci.arep.entity.User;
import edu.eci.arep.repository.MongoDbRepositoryUser;

import java.util.List;

public class UserService {
    private final MongoDbRepositoryUser mongoDbRepository;

    public UserService(MongoDbRepositoryUser mongoDbRepositoryUser) {
        this.mongoDbRepository = mongoDbRepositoryUser;
        this.mongoDbRepository.createConnection("userdb","users");
    }


    public List<User> getAllUsers(){
        return mongoDbRepository.getAllUsers();
    }
}
