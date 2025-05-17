package com.ertedemo.domain.model.entites;

import com.ertedemo.api.resource.posts.CreatePostResource;
import com.ertedemo.api.resource.posts.UpdatePostResource;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name ="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String location;

    @NotNull
    private Float price;

    @NotNull
    @NotBlank
    private String category;

    @NotNull
    @NotBlank
    private String urlPhoto;

    private Boolean available = true;

    @NotNull
    private Integer rooms;

    @NotNull
    private Integer bathrooms;

    private Boolean pets;

    private Boolean smoking;

    @ManyToOne
    @JoinColumn(name = "landlord_id")
    @JsonBackReference
    private Landlord landlord;

    public Post(Landlord landlord, CreatePostResource resource) {
        this.title = resource.getTitle();
        this.description = resource.getDescription();
        this.location = resource.getLocation();
        this.price = resource.getPrice();
        this.category = resource.getCategory();
        this.urlPhoto = resource.getUrlPhoto();
        this.available = resource.getAvailable();
        this.rooms = resource.getRooms();
        this.bathrooms = resource.getBathrooms();
        this.pets = resource.getPets();
        this.smoking = resource.getSmoking();
        this.landlord = landlord;
    }

    public void updatePost(UpdatePostResource resource) {
        this.title = resource.getTitle();
        this.description = resource.getDescription();
        this.location = resource.getLocation();
        this.price = resource.getPrice();
        this.category = resource.getCategory();
        this.urlPhoto = resource.getUrlPhoto();
        this.available = resource.getAvailable();
        this.rooms = resource.getRooms();
        this.bathrooms = resource.getBathrooms();
        this.pets = resource.getPets();
        this.smoking = resource.getSmoking();
    }
}