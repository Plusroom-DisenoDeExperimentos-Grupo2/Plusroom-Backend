package com.ertedemo.domain.model.entites;

import com.ertedemo.api.resource.landlord.CreateLandlordResource;
import com.ertedemo.api.resource.landlord.UpdateLandlordResource;
import com.ertedemo.api.resource.tenant.CreateTenantResource;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "landlords")

public class Landlord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "description")
    private String description;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "gender")
    private String gender;

    @OneToMany(mappedBy = "landlord", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Post> posts;

    @OneToMany(mappedBy = "landlord")
    private List<Customer> customers;

    @Column(name = "rating")
    private Float rating;

    //@OneToMany(mappedBy = "recipient")
    //private List<Message> receivedMessages;

    //@OneToMany(mappedBy = "author")
    //private List<Message> sentMessages;

    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "landlord")
    @JsonManagedReference
    private List<Notification> listNotification;

    public Landlord() {
    }

    public Landlord(CreateLandlordResource resource) {
        this.name = resource.getName();
        this.lastName = resource.getLastName();
        this.email = resource.getEmail();
        this.password = resource.getPassword();
        this.description = resource.getDescription();
        this.age = resource.getAge();
        this.gender = resource.getGender();
        this.photo = resource.getPhoto();
    }

    public void updateLandlord(UpdateLandlordResource resource) {
        this.name = resource.getName();
        this.lastName = resource.getLastName();
        //this.phoneNumber = resource.getPhoneNumber();
        this.email = resource.getEmail();
        this.age = resource.getAge();
        this.description = resource.getDescription();
        this.gender = resource.getGender();
        this.photo = resource.getPhoto();
    }
}