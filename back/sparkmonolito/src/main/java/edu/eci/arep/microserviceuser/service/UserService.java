package edu.eci.arep.microserviceuser.service;

import edu.eci.arep.microserviceuser.entity.User;
import edu.eci.arep.microserviceuser.repository.MongoDbRepositoryUser;

import java.util.ArrayList;

public class UserService {
    private final MongoDbRepositoryUser mongoDbRepository;

    public UserService(MongoDbRepositoryUser mongoDbRepositoryUser) {
        this.mongoDbRepository = mongoDbRepositoryUser;
        this.mongoDbRepository.createConnection("userdb","users");
    }


    public ArrayList<User> getAllUsers(){
        return mongoDbRepository.getAllUsers();
    }
}
