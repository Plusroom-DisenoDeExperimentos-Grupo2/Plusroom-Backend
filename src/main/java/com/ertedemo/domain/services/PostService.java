package com.ertedemo.domain.services;

import com.ertedemo.api.resource.posts.CreatePostResource;
import com.ertedemo.domain.model.entites.Landlord;
import com.ertedemo.domain.model.entites.Post;
import com.ertedemo.domain.model.entites.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAll();
    Optional<Post> getById(Long postId);
    Optional<Post> create(Post post);
    Optional<Post> update(Post post);
    ResponseEntity<?> delete(Long postId);

    List<Post> getByLandlord(Landlord landlord);
}
