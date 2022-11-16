package edu.eci.arep.microserviceuser;


import edu.eci.arep.microserviceuser.service.UserService;
import edu.eci.arep.microservicepost.repository.MongoDbRepositoryPost;

import static spark.Spark.*;

public class controller {


    public static void main(String... args){
        MongoDbRepository mongoDbRepository = new MongoDbRepository();
        port(getPort());

        //User
        path("/user", () -> {
            MongoDbRepositoryPost mongoDbRepositoryUser = new MongoDbRepositoryPost();
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
