package com.ertedemo.api.resource.tenant;

import lombok.Data;

@Data
public class CreateTenantResource {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String description;
    private String dni;
    private Integer age;
    private String gender;
    private String occupation;
    private Boolean searchRoomie;
    private String photo;
}