package edu.eci.arep;


import com.google.gson.*;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.ResourceRetriever;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import edu.eci.arep.entity.Post;
import edu.eci.arep.repository.MongoDbRepository;
import edu.eci.arep.repository.MongoDbRepositoryUser;
import edu.eci.arep.service.PostService;
import edu.eci.arep.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.eclipse.jetty.http.HttpStatus;
import spark.Response;

import javax.json.Json;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class SparkWeb {


    public static void main(String... args){
        MongoDbRepository mongoDbRepository = new MongoDbRepository();
        port(getPort());
        //Post
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
        path("/user", () -> {
            MongoDbRepositoryUser mongoDbRepositoryUser = new MongoDbRepositoryUser();
            UserService userService =  new UserService(mongoDbRepositoryUser);
            post("/auth", (req, res) -> {
                //verify if user exist
                String SERVER_KEY = "password";
                String jwt = Jwts.builder()
                                .signWith(SignatureAlgorithm.HS256, SERVER_KEY)
                                .setSubject("admin").compact();
                javax.json.JsonObject json = Json.createObjectBuilder()
                        .add("JWT",jwt).build();
                res.type("application/json");
                res.body("hi");
                return json;
            });
            get("", (req, res) -> {
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
