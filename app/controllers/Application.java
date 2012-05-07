package controllers;

import models.User;

import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import play.mvc.Controller;
import play.mvc.Result;
import plugins.JongoPlugin;

public class Application extends Controller {

    public static Result index() throws Exception {

        JongoPlugin plugin = JongoPlugin.getJongoPlugin();
        Jongo jongo = plugin.getJongo("myDatabase");
        MongoCollection collection = jongo.getCollection("users");

        String id = collection.save(new User("Peter"));
        User user = collection.findOne(new ObjectId(id)).as(User.class);

        return ok("A new user has been added id:" + id + ", name:" + user.getName());
    }

}
