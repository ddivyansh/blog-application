package com.example.blogApplication.service.impl;

import com.example.blogApplication.entity.Post;
import com.example.blogApplication.exceptions.ResourceNotFoundException;
import com.example.blogApplication.payload.PostDto;
import com.example.blogApplication.payload.PostResponse;
import com.example.blogApplication.repository.PostRepository;
import com.example.blogApplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * An implementation of our PostService, @Service annotation so it can be detected during component scanning.
 * Since it's the only implementation we don't really need to qualify it.
 * Now we need an instance of PostRepository for this, hence autowired.
 * Instead of returning a list we're returning an object. It's good practice.
 * resetAutoIncrement() : Post deletion of all the entities, we're resetting the id value to 0
 * Implemented pagination in getAllPost() method.
 * 1. creating an instance of pageable by calling pagerequest.of(Pageno, size)
 * 2. getting content from pages using getContent.
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        /*
         * Convert the postDto object into Post entity.
         * Save the entity into db.
         * return the saved post.
         */
        Post post = mapToPost(postDto);
        Post newPost = postRepository.save(post); // it returns the saved entity
        return mapToPostDto(newPost);
    }

    // Instead of returning a list we're returning an object. It's good practice.
    @Override
    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        //constructing a sort object whose direction depends on the value of sortDir i.e. asc/desc
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> listOfPostDtos = listOfPosts.stream().map(this::mapToPostDto).toList();
        return new PostResponse(listOfPostDtos, pageNo, pageSize, posts.getNumber(), posts.getSize(), posts.isLast());
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Post", "Id", id);
        });
        return mapToPostDto(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Post", "Id", id);
        });
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        Post updatedPost = postRepository.save(post);
        return mapToPostDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("post", "id", id);
        });
        postRepository.delete(post);
        //resetting the id when the count becomes zero
        if (postRepository.count() == 0) {
            String tableName = "Posts";
            resetAutoIncrement(tableName);
        }
    }

    @Override
    public void resetAutoIncrement(String tableName) {
        postRepository.resetAutoIncrement(tableName);
    }

    private Post mapToPost(PostDto postDto) {
        Post post = new Post();
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());
        return post;
    }

    private PostDto mapToPostDto(Post post) {
        PostDto postResponseDto = new PostDto();
        postResponseDto.setId(post.getId());
        postResponseDto.setContent(post.getContent());
        postResponseDto.setDescription(post.getDescription());
        postResponseDto.setTitle(post.getTitle());
        return postResponseDto;
    }
}