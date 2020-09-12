package com.alice.afroapp;

public class Question {

    String body;
    String user;
    String Id;

    public Question(String body, String user, String id) {
        this.body = body;
        this.user = user;
        Id = id;
    }

    public Question(){}


    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    public String getUser() { return user; }

    public void setUser(String user) { this.user = user; }

    public String getId() { return Id; }

    public void setId(String id) { Id = id; }




}
