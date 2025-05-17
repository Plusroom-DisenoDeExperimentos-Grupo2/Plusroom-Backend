package com.ertedemo.api.resource.roomie;

import com.ertedemo.domain.model.entites.RoomiePreference;
import lombok.Data;
import java.util.List;

@Data
public class RoomiePreferenceResource {
    private Long id;
    private Long tenantId;
    private List<String> preferences;
    private List<String> hobbies;
    private String locationPreference;
    private Float budget;
    private String genderPreference;
    private Integer minAge;
    private Integer maxAge;
    private Boolean petFriendly;
    private Boolean smokingPreference;
    private String cleaningHabits;
    private String sleepingHabits;

    public RoomiePreferenceResource(RoomiePreference roomiePreference) {
        this.id = roomiePreference.getId();
        this.tenantId = roomiePreference.getTenant().getId();
        this.preferences = roomiePreference.getPreferences();
        this.hobbies = roomiePreference.getHobbies();
        this.locationPreference = roomiePreference.getLocationPreference();
        this.budget = roomiePreference.getBudget();
        this.genderPreference = roomiePreference.getGenderPreference();
        this.minAge = roomiePreference.getMinAge();
        this.maxAge = roomiePreference.getMaxAge();
        this.petFriendly = roomiePreference.getPetFriendly();
        this.smokingPreference = roomiePreference.getSmokingPreference();
        this.cleaningHabits = roomiePreference.getCleaningHabits();
        this.sleepingHabits = roomiePreference.getSleepingHabits();
    }
}