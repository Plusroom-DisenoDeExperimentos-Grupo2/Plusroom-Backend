package com.ertedemo.api.resource.landlord;

import com.ertedemo.domain.model.entites.Landlord;
import lombok.Data;

@Data
public class LandlordResponse {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Integer age;
    private String gender;
    private String description;
    private Float rating;
    private String photo;

    public LandlordResponse(Landlord landlord) {
        this.id = landlord.getId();
        this.name = landlord.getName();
        this.lastName = landlord.getLastName();
        this.email = landlord.getEmail();
        this.age = landlord.getAge();
        this.gender = landlord.getGender();
        this.description = landlord.getDescription();
        this.rating = landlord.getRating();
        this.photo = landlord.getPhoto();
    }
}