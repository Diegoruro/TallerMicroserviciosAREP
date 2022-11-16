package edu.eci.arep.microservicepost;


import com.google.gson.*;
import edu.eci.arep.microservicepost.entity.Post;
import edu.eci.arep.microservicepost.repository.MongoDbRepositoryPost;
import edu.eci.arep.microservicepost.service.PostService;

import static spark.Spark.*;

public class SparkWeb {


    public static void main(String... args){
        MongoDbRepositoryPost mongoDbRepository = new MongoDbRepositoryPost();
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

    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }


}
