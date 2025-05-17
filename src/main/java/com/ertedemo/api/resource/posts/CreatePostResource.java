package com.ertedemo.api.resource.posts;

import lombok.Data;

@Data
public class CreatePostResource {
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
}