package com.ertedemo.api.resource.tenant;

import com.ertedemo.domain.model.entites.Tenant;
import lombok.Data;

@Data
public class TenantResponse {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String description;
    private String dni;
    private Integer age;
    private String gender;
    private String occupation;
    //private Boolean searchRoomie;
    private String photo;

    public TenantResponse(Tenant tenant) {
        this.id = tenant.getId();
        this.name = tenant.getName();
        this.lastName = tenant.getLastName();
        this.email = tenant.getEmail();
        this.description = tenant.getDescription();
        this.dni = tenant.getDni();
        this.age = tenant.getAge();
        this.gender = tenant.getGender();
        this.occupation = tenant.getOccupation();
        //this.searchRoomie = tenant.getSearchRoomie();
        this.photo = tenant.getPhoto();
    }
}