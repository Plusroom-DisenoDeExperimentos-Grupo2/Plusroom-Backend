package com.ertedemo.domain.model.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="roomie_preferences")
public class RoomiePreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "tenant_id", nullable = false, foreignKey = @ForeignKey(name = "FK_roomie_preferences_tenant"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Tenant tenant;

    @ElementCollection
    @CollectionTable(name = "preferences", joinColumns = @JoinColumn(name = "roomie_preference_id"))
    @Column(name = "preference")
    private List<String> preferences;

    @ElementCollection
    @CollectionTable(name = "hobbies", joinColumns = @JoinColumn(name = "roomie_preference_id"))
    @Column(name = "hobby")
    private List<String> hobbies;

    @NotNull
    private String locationPreference;

    @NotNull
    private Float budget;

    @NotNull
    private String genderPreference;

    @NotNull
    private Integer minAge;

    @NotNull
    private Integer maxAge;

    @NotNull
    private Boolean petFriendly;

    @NotNull
    private Boolean smokingPreference;

    @NotNull
    private String cleaningHabits;

    @NotNull
    private String sleepingHabits;
}