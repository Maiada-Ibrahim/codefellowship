package com.example.codefellowship;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String body;
    private String createdAt;

    @ManyToOne
    private ApplicationUser owner;

    public Post() {

    }

    public Post(String body, String createdAt, ApplicationUser owner) {
        this.body = body;
        this.createdAt = createdAt;
        this.owner = owner;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body ) {
        this.body = body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }
}
