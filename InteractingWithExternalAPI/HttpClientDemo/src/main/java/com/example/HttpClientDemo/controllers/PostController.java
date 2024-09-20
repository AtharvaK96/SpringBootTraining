package com.example.HttpClientDemo.controllers;

import com.example.HttpClientDemo.model.Post;
import com.example.HttpClientDemo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        String postRes = postService.addPost(post).orElseThrow(() -> new RuntimeException("Unable to add the post"));
        return new ResponseEntity<>(postRes,HttpStatus.CREATED);


    }

    @GetMapping
    public ResponseEntity<String> getAllPosts() {
        String posts = postService.getAllPosts().orElseThrow(() -> new RuntimeException("NO posts available"));
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        String postUpdated = postService.updatePost(id, postDetails).orElseThrow(() -> new RuntimeException("unable to update the post"));
        return new ResponseEntity<>(postUpdated,HttpStatus.OK);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        String post = postService.deletePost(id).orElseThrow(() -> new RuntimeException("unable to delete the post"));
        return new ResponseEntity<>(post,HttpStatus.OK);

    }

}
