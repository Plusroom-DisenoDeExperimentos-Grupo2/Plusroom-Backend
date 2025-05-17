package com.ertedemo.api.resource.landlord;

import lombok.Data;

@Data
public class UpdateLandlordResource {
    private String name;
    private String lastName;
    //private String phoneNumber;
    private String email;
    private Integer age;
    private String description;
    private String gender;
    private String photo;
}
