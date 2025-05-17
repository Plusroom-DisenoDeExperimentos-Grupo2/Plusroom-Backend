package com.ertedemo.api;

import com.ertedemo.api.resource.roomie.CreateRoomiePreferenceResource;
import com.ertedemo.api.resource.roomie.RoomiePreferenceResource;
import com.ertedemo.domain.model.entites.RoomiePreference;
import com.ertedemo.domain.model.entites.Tenant;
import com.ertedemo.domain.model.entites.User;
import com.ertedemo.domain.services.RoomiePreferenceService;
import com.ertedemo.domain.services.TenantService;
import com.ertedemo.shared.exception.ResourceNotFoundException;
import com.ertedemo.shared.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roomies")
public class RoomieController {

    @Autowired
    private RoomiePreferenceService roomiePreferenceService;

    @Autowired
    private TenantService tenantService;

    @GetMapping("/search")
    public List<Tenant> searchRoomies(
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "budget", required = false) Float budget) {
        return roomiePreferenceService.findByLocationAndBudget(location, budget);
    }

    @GetMapping("/search/preferences")
    public RoomiePreference searchRoomiesByTenantId(
            @RequestParam(value = "tenantId", required = false) Long tenantId) {
        return roomiePreferenceService.findByTenantId(tenantId);
    }

    @PostMapping("/preferences")
    public ResponseEntity<BaseResponse<RoomiePreferenceResource>> savePreferences(@RequestParam Long tenantId, @RequestBody CreateRoomiePreferenceResource resource) {
        try {
            RoomiePreference preferences = new RoomiePreference();
            var tenant = tenantService.getById(tenantId)
                    .orElseThrow(() -> new ResourceNotFoundException("The tenant with id " +
                            tenantId + " does not exist"));
            preferences.setTenant(tenant);
            preferences.setPreferences(resource.getPreferences());
            preferences.setHobbies(resource.getHobbies());
            preferences.setLocationPreference(resource.getLocationPreference());
            preferences.setBudget(resource.getBudget());
            preferences.setGenderPreference(resource.getGenderPreference());
            preferences.setMinAge(resource.getMinAge());
            preferences.setMaxAge(resource.getMaxAge());
            preferences.setPetFriendly(resource.getPetFriendly());
            preferences.setSmokingPreference(resource.getSmokingPreference());
            preferences.setCleaningHabits(resource.getCleaningHabits());
            preferences.setSleepingHabits(resource.getSleepingHabits());

            RoomiePreference savedPreferences = roomiePreferenceService.savePreferences(preferences);
            RoomiePreferenceResource response = new RoomiePreferenceResource(savedPreferences);

            BaseResponse<RoomiePreferenceResource> baseResponse = new BaseResponse<>(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(baseResponse);
        } catch (DataIntegrityViolationException ex) {
            BaseResponse<RoomiePreferenceResource> errorResponse = new BaseResponse<>(null, "USER_ALREADY_HAS_PREFERENCES", "Este usuario ya tiene preferencias creadas.", true);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }

    @PutMapping("/preferences")
    public ResponseEntity<BaseResponse<RoomiePreferenceResource>> updatePreferences(@RequestParam Long tenantId, @RequestBody CreateRoomiePreferenceResource resource) {
        try {
            RoomiePreference existingPreferences = roomiePreferenceService.findByTenantId(tenantId);
            if (existingPreferences == null) {
                BaseResponse<RoomiePreferenceResource> errorResponse = new BaseResponse<>(null, "PREFERENCES_NOT_FOUND", "No se encontraron preferencias para este usuario.", true);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }

            existingPreferences.setPreferences(resource.getPreferences());
            existingPreferences.setHobbies(resource.getHobbies());
            existingPreferences.setLocationPreference(resource.getLocationPreference());
            existingPreferences.setBudget(resource.getBudget());
            existingPreferences.setGenderPreference(resource.getGenderPreference());
            existingPreferences.setMinAge(resource.getMinAge());
            existingPreferences.setMaxAge(resource.getMaxAge());
            existingPreferences.setPetFriendly(resource.getPetFriendly());
            existingPreferences.setSmokingPreference(resource.getSmokingPreference());
            existingPreferences.setCleaningHabits(resource.getCleaningHabits());
            existingPreferences.setSleepingHabits(resource.getSleepingHabits());

            RoomiePreference updatedPreferences = roomiePreferenceService.savePreferences(existingPreferences);
            RoomiePreferenceResource response = new RoomiePreferenceResource(updatedPreferences);

            BaseResponse<RoomiePreferenceResource> baseResponse = new BaseResponse<>(response);
            return ResponseEntity.status(HttpStatus.OK).body(baseResponse);
        } catch (Exception ex) {
            BaseResponse<RoomiePreferenceResource> errorResponse = new BaseResponse<>(null, "UPDATE_FAILED", "No se pudieron actualizar las preferencias.", true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}