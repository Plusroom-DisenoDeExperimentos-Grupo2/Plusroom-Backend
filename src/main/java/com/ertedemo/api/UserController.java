package com.ertedemo.api;

import com.ertedemo.api.resource.users.CreateUserResource;
import com.ertedemo.api.resource.users.LoginCredential;
import com.ertedemo.api.resource.users.UpdateUserResource;
import com.ertedemo.api.resource.users.UserResponse;
import com.ertedemo.domain.model.entites.User;
import com.ertedemo.domain.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
//@RestController
//@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> responseList = userService.getAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.getById(userId);
        return user.map(value -> ResponseEntity.ok(new UserResponse(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserResource resource) {
        Optional<User> user = userService.create(new User(resource));
        return user.map(value -> ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping()
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserResource resource) {
        Optional<User> user = userService.getById(resource.getId());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        user.get().updateUser(resource);
        User updatedUser = userService.update(user.get()).get();
        return ResponseEntity.ok(new UserResponse(updatedUser));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        ResponseEntity<?> result = userService.delete(userId);
        if (result.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/rate-user/{userId}/{rate}")
    public ResponseEntity<UserResponse> rateUser(@PathVariable Long userId, @PathVariable Float rate) {
        Optional<User> user = userService.getById(userId);
        if (rate < 0 || rate > 5 || user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        user.get().setRankPoints(rate);
        Optional<User> updatedUser = userService.update(user.get());
        return ResponseEntity.ok(new UserResponse(updatedUser.get()));
    }

    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody LoginCredential loginCredential) {
        Long userId = userService.login(loginCredential.getEmail(), loginCredential.getPassword());
        if (userId != null) {
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
