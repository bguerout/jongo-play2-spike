package models;

import org.bson.types.ObjectId;

public class User {

    private ObjectId _id;
    private String name;

    public User() {
        super();
    }

    public User(String name) {
        super();
        this.name = name;
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

}
