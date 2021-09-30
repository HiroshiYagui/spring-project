package com.freecode.redditclone.controller;

import java.util.List;

import com.freecode.redditclone.dto.PostRequest;
import com.freecode.redditclone.dto.PostResponse;
import com.freecode.redditclone.service.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.ResponseEntity.status;

@RequestMapping("api/posts")
@RestController
@AllArgsConstructor
@Slf4j
public class PostController {
    
    private final PostService postService;


    @PostMapping
    public ResponseEntity<Void> CreatePost(@RequestBody PostRequest postRequest){
        postService.create(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id){
        return status(HttpStatus.OK).body(postService.getPost(id));
    }

    @GetMapping("by-subreddit/{id}")
    public ResponseEntity<List<PostResponse>> getPostBySubreddit(Long id){
        return status(HttpStatus.OK).body(postService.getPostBySubreddit(id));
    }

    @GetMapping("by-user/{id}")
    public ResponseEntity<List<PostResponse>> getPostByUsername(@PathVariable String username){
        return status(HttpStatus.OK).body(postService.getPostByUsernarme(username));
    }
}
