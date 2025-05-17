package com.ertedemo.api.resource.posts;

import com.ertedemo.domain.model.entites.Post;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostResponse {
    private Long id;
    private String title;
    private String description;
    private String location;
    private Float price;
    private String category;
    private String urlPhoto;
    private Boolean available;
    private Integer rooms;
    private Integer bathrooms;
    private Boolean pets;
    private Boolean smoking;
    private Long landlordId;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.location = post.getLocation();
        this.price = post.getPrice();
        this.category = post.getCategory();
        this.urlPhoto = post.getUrlPhoto();
        this.available = post.getAvailable();
        this.rooms = post.getRooms();
        this.bathrooms = post.getBathrooms();
        this.pets = post.getPets();
        this.smoking = post.getSmoking();
        this.landlordId = post.getLandlord().getId();
    }
}