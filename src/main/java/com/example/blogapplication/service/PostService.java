package com.example.blogapplication.service;

import com.example.blogapplication.payload.PostDto;
import com.example.blogapplication.utils.PostsDtoList;


public interface PostService {
    public PostDto createPost(PostDto postDto);

    public PostsDtoList getAllPost();

    public PostDto getPostById(long id);

    public PostDto updatePostById(PostDto postDto, long id);

    void deletePostById(long id);
}
