package com.ertedemo.api.resource.landlord;

import lombok.Data;

@Data
public class CreateLandlordResource {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String description;
    private Integer age;
    private String gender;
    private String photo;
}