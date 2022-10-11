package com.example.blogApplication.service;

import com.example.blogApplication.payload.PostDto;
import com.example.blogApplication.payload.PostResponse;
import com.example.blogApplication.utils.PostsDtoList;


public interface PostService {
    public PostDto createPost(PostDto postDto);

    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    public PostDto getPostById(long id);

    public PostDto updatePostById(PostDto postDto, long id);

    void deletePostById(long id);

    void resetAutoIncrement(String tableName);
}
