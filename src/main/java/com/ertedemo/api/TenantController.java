package com.ertedemo.api;

import com.ertedemo.api.resource.tenant.CreateTenantResource;
import com.ertedemo.api.resource.tenant.TenantResponse;
import com.ertedemo.api.resource.tenant.UpdateTenantResource;
import com.ertedemo.api.resource.users.LoginCredential;
import com.ertedemo.domain.model.entites.Tenant;
import com.ertedemo.domain.services.TenantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    private final TenantService tenantService;

    @GetMapping
    public ResponseEntity<List<TenantResponse>> getAllTenants() {
        List<TenantResponse> responseList = tenantService.getAll().stream()
                .map(TenantResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("{tenantId}")
    public ResponseEntity<TenantResponse> getTenantById(@PathVariable Long tenantId) {
        Optional<Tenant> tenant = tenantService.getById(tenantId);
        return tenant.map(value -> ResponseEntity.ok(new TenantResponse(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/createTenant")
    public ResponseEntity<TenantResponse> createTenant(@RequestBody CreateTenantResource resource) {
        Tenant tenant = tenantService.create(new Tenant(resource));
        return ResponseEntity.status(HttpStatus.CREATED).body(new TenantResponse(tenant));
    }

    @PutMapping("{tenantId}")
    public ResponseEntity<TenantResponse> updateTenant(@PathVariable Long tenantId, @RequestBody UpdateTenantResource resource) {
        Optional<Tenant> tenant = tenantService.getById(tenantId);
        if (tenant.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        tenant.get().updateTenant(resource);
        Tenant updatedTenant = tenantService.update(tenant.get());
        return ResponseEntity.ok(new TenantResponse(updatedTenant));
    }

    @DeleteMapping("{tenantId}")
    public ResponseEntity<Void> deleteTenant(@PathVariable Long tenantId) {
        ResponseEntity<?> result = tenantService.delete(tenantId);
        if (result.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody LoginCredential loginCredential) {
        Long tenantId = tenantService.login(loginCredential.getEmail(), loginCredential.getPassword());
        if (tenantId != null) {
            return ResponseEntity.ok(tenantId);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}