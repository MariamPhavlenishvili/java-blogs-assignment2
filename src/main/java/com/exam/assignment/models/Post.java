package com.exam.assignment.models;

import java.sql.Timestamp;

public class Post {
    private int id;
    private String title;
    private String metaTitle;
    private String category;
    private String content;
    private String author;
    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;

    public Post(int id, String title, String metaTitle, String category, String content, String author, java.sql.Timestamp createdAt, java.sql.Timestamp updatedAt) {
        this.id = id;
        this.title = title;
        this.metaTitle = metaTitle;
        this.category = category;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public String getCategory() {
        return category;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
