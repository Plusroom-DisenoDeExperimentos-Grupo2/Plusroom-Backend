package com.ertedemo.api;

import com.ertedemo.api.resource.landlord.CreateLandlordResource;
import com.ertedemo.api.resource.landlord.UpdateLandlordResource;
import com.ertedemo.api.resource.users.LoginCredential;
import com.ertedemo.domain.model.entites.Landlord;
import com.ertedemo.domain.services.LandlordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/landlords")
@AllArgsConstructor
public class LandlordController {

    private final LandlordService landlordService;

    @GetMapping
    public ResponseEntity<List<Landlord>> getAllLandlords() {
        List<Landlord> landlords = landlordService.getAll();
        return ResponseEntity.ok(landlords);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Landlord> getLandlordById(@PathVariable("id") Long id) {
        return landlordService.getById(id)
                .map(landlord -> ResponseEntity.ok(landlord))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Landlord> createLandlord(@RequestBody CreateLandlordResource resource) {
        Landlord createdLandlord = landlordService.create(new Landlord(resource));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLandlord);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Landlord> updateLandlord(@PathVariable("id") Long id, @RequestBody UpdateLandlordResource resource) {
        Optional<Landlord> landlord = landlordService.getById(id);
        if (landlord.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        landlord.get().updateLandlord(resource);
        Landlord updatedLandlord = landlordService.update(landlord.get());
        return ResponseEntity.ok(updatedLandlord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLandlord(@PathVariable("id") Long id) {
        landlordService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody LoginCredential loginCredential) {
        Long landlordId = landlordService.login(loginCredential.getEmail(), loginCredential.getPassword());
        if (landlordId != null) {
            return ResponseEntity.ok(landlordId);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}