package edu.eci.arep;


import com.google.gson.*;
import edu.eci.arep.entity.Post;
import edu.eci.arep.repository.MongoDbRepository;
import edu.eci.arep.service.PostService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.eclipse.jetty.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class SparkWeb {




    public static void main(String... args){
        MongoDbRepository mongoDbRepository = new MongoDbRepository();
        port(getPort());
        post("post", (req,res) -> {
            PostService postService = new PostService(mongoDbRepository);
            JsonObject json = JsonParser.parseString(req.body()).getAsJsonObject();
            Post post = new Post(json.get("text").toString(),json.get("userName").toString(),json.get("date").toString());

            postService.addPost(post);
            return post;
        });
        get("posts", (req,res)->{
            PostService postService = new PostService(mongoDbRepository);


            return postService.getAllPost();
        });


        //User
        path("", () -> {
            post("/auth", (req, res) -> {
                //verify if user exist
                String SERVER_KEY = "password";
                String jwt = Jwts.builder()
                                .signWith(SignatureAlgorithm.ES256, SERVER_KEY)
                                .setSubject("admin").compact();
                JsonObject json = new JsonObject();
                json.addProperty("JWT",jwt);
                res.body("hi");
                return "ok";
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
