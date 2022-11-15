package edu.eci.arep.microserviceuser;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.eci.arep.entity.Post;
import edu.eci.arep.repository.MongoDbRepository;
import edu.eci.arep.repository.MongoDbRepositoryUser;
import edu.eci.arep.service.PostService;
import edu.eci.arep.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.json.Json;

import static spark.Spark.*;

public class controller {


    public static void main(String... args){
        MongoDbRepository mongoDbRepository = new MongoDbRepository();
        port(getPort());

        //User
        path("/user", () -> {
            MongoDbRepositoryUser mongoDbRepositoryUser = new MongoDbRepositoryUser();
            UserService userService =  new UserService(mongoDbRepositoryUser);
            post("/auth", (req, res) -> {
                //verify if user exist
                return "ok";
            });
            get("/getall", (req, res) -> {
                return userService.getAllUsers();
            });
        });

    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }


}
