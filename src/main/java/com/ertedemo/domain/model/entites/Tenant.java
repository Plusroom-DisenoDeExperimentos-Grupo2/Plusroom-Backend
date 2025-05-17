package com.ertedemo.domain.model.entites;

import com.ertedemo.api.resource.tenant.CreateTenantResource;
import com.ertedemo.api.resource.tenant.UpdateTenantResource;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tenants")
public class Tenant {
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

    @Column(name = "dni", nullable = false)
    private String dni;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "occupation")
    private String occupation;

    //@OneToMany(mappedBy = "tenant")
    //private List<Customer> customers;

    //@OneToMany(mappedBy = "recipient") // Mensajes recibidos por el tenant
    //private List<Message> receivedMessages;

    //@OneToMany(mappedBy = "author") // Mensajes enviados por el tenant
    //private List<Message> sentMessages;


    @Column(name = "photo")
    private String photo;

    //
    @ManyToMany
    @JoinTable(
            name = "tenant_notification",
            joinColumns = @JoinColumn(name = "tenant_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id")
    )
    private List<Notification> listNotification;


    public Tenant() {
    }

    public Tenant(CreateTenantResource resource) {
        this.name = resource.getName();
        this.lastName = resource.getLastName();
        this.email = resource.getEmail();
        this.password = resource.getPassword();
        this.description = resource.getDescription();
        this.dni = resource.getDni();
        this.age = resource.getAge();
        this.gender = resource.getGender();
        this.occupation = resource.getOccupation();
        this.photo = resource.getPhoto();
    }

    public void updateTenant(UpdateTenantResource resource) {
        this.name = resource.getName();
        this.lastName = resource.getLastName();
        this.email = resource.getEmail();
        this.description = resource.getDescription();
        this.dni = resource.getDni();
        this.age = resource.getAge();
        this.gender = resource.getGender();
        this.occupation = resource.getOccupation();
        this.photo = resource.getPhoto();
    }
}