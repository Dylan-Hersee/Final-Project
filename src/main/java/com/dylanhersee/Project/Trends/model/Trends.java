package com.dylanhersee.Project.Trends.model;

import org.springframework.data.mongodb.core.mapping.Document;

 @Document(collection = "trends")

public class Trends {

   

    private String id;

    private String username;
    private String eventName;
    private String trendName;
    private String trendDescription;
    private String trendCategory;
    private String source;
    private String publishedDate;
    private String imageUrl;
    private String trendUrl;
    

    public Trends(String id, String username, String eventName, String trendName, String trendDescription, String trendCategory, String source, String publishedDate, String imageUrl, String trendUrl) {
        this.id = id;
        this.username = username;
        this.eventName = eventName;
        this.trendName = trendName;
        this.trendDescription = trendDescription;
        this.trendCategory = trendCategory;
        this.source = source;
        this.publishedDate = publishedDate;
        this.imageUrl = imageUrl;
        this.trendUrl = trendUrl;
    }

    public Trends(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getEventName() {
        return eventName;
    }

    public String getTrendName() {
        return trendName;
    }

    public String getTrendDescription() {
        return trendDescription;
    }

    public String getTrendCategory() {
        return trendCategory;
    }

    public String getSource() {
        return source;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTrendUrl() {
        return trendUrl;
    }


    public String getId() {
        return id;
    }


}
