package edu.eci.arep.service;

import edu.eci.arep.entity.User;
import java.util.List;
import edu.eci.arep.repository.MongoDbRepositoryUser;

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
