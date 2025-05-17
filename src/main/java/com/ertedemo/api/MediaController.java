package com.ertedemo.api;

import com.ertedemo.api.resource.posts.PostResponse;
import com.ertedemo.domain.model.entites.Post;
import com.ertedemo.domain.services.PostService;
import com.ertedemo.shared.services.media.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    private final PostService postService;
    private final StorageService storageService;
    private final HttpServletRequest request;

    public MediaController(PostService postService, StorageService storageService, HttpServletRequest request) {
        this.postService = postService;
        this.storageService = storageService;
        this.request = request;
    }

    @PostMapping("/post/{postId}/upload")
    public ResponseEntity<PostResponse> uploadFiles(
            @PathVariable Long postId,
            @RequestParam("files") List<MultipartFile> files) {

        Optional<Post> post = postService.getById(postId);

        if (post.isEmpty())
            return ResponseEntity.notFound().build();

        if (!files.isEmpty()) {
            MultipartFile file = files.get(0); // Solo se usa el primer archivo
            String path = storageService.storage(file);
            String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
            String url = ServletUriComponentsBuilder
                    .fromHttpUrl(host)
                    .path("/api/media/")
                    .path(path)
                    .toUriString();

            post.get().setUrlPhoto(url);
            Optional<Post> postWithImage = postService.update(post.get());

            return ResponseEntity.ok(new PostResponse(postWithImage.get()));
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
        Resource file = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);
    }
}