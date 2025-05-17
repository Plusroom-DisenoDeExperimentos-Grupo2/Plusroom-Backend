package com.ertedemo.api.resource.roomie;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateRoomiePreferenceResource {
    @NotNull
    private List<String> preferences;

    @NotNull
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