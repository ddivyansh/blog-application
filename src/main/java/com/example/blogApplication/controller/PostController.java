package com.example.blogApplication.controller;

import com.example.blogApplication.payload.PostDto;
import com.example.blogApplication.service.PostService;
import com.example.blogApplication.utils.PostsDtoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * We're implementing a loose coupled approach hence we autowire the interface not the class.
 * Since there's a single implementation of service class spring will find the implementation as long as the component scanning is enabled, and the impl is annotated with some annotation which enables its detection during component scan.
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    PostService postService;

    //for post requests : creating a post
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // get request to get all posts as an obj
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PostsDtoList getAllPosts() {
        return postService.getAllPost();
    }

    //get request to get post by id
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //put request to update post by id
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable("id") long id) {
        return ResponseEntity.ok(postService.updatePostById(postDto, id));
    }

    //delete request to delete post by id
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletePostById(@PathVariable long id){
        postService.deletePostById(id);
        return new ResponseEntity("Post deleted successfully !", HttpStatus.OK);
    }
}
