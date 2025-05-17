package com.ertedemo.domain.model.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    @ElementCollection
    @CollectionTable(name = "notification_tenant_ids", joinColumns = @JoinColumn(name = "notification_id"))
    @Column(name = "tenant_id")
    private List<Long> tenantIds;

    @ManyToOne
    @JoinColumn(name = "landlord_id")
    @JsonBackReference
    private Landlord landlord;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToMany(mappedBy = "listNotification")
    private List<Tenant> tenants;

    public Notification() {
    }

    public Notification(List<Long> tenantIds, Long postId, Date date) {
        this.tenantIds = tenantIds;
        this.postId = postId;
        this.date = date;
    }
}