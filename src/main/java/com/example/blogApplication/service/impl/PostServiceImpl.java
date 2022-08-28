package com.example.blogApplication.service.impl;

import com.example.blogApplication.entity.Post;
import com.example.blogApplication.exceptions.ResourceNotFoundException;
import com.example.blogApplication.payload.PostDto;
import com.example.blogApplication.repository.PostRepository;
import com.example.blogApplication.service.PostService;
import com.example.blogApplication.utils.PostsDtoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * An implementation of our PostService, @Service annotation so it can be detected during component scanning.
 * Since it's the only implementation we don't really need to qualify it.
 * Now we need an instance of PostRepository for this, hence autowired.
 * Instead of returning a list we're returning an object. It's good practice.
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        /**
         * Convert the postDto object into Post entity.
         * Save the entity into db.
         * return the saved post.
         */
        Post post = mapToPost(postDto);
        Post newPost = postRepository.save(post); // it returns the saved entity
        PostDto postResponseDto = mapToPostDto(newPost);
        return postResponseDto;
    }

    // Instead of returning a list we're returning an object. It's good practice.
    @Override
    public PostsDtoList getAllPost() {
        List<Post> listOfPosts = postRepository.findAll();
        List<PostDto> listOfPostDtos = listOfPosts.stream().map(this::mapToPostDto).toList();
        return new PostsDtoList(listOfPostDtos);
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
