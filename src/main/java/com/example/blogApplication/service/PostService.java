package com.example.blogApplication.service;

import com.example.blogApplication.payload.PostDto;
import com.example.blogApplication.utils.PostsDtoList;


public interface PostService {
    public PostDto createPost(PostDto postDto);

    public PostsDtoList getAllPost(int pageNo, int pageSize);

    public PostDto getPostById(long id);

    public PostDto updatePostById(PostDto postDto, long id);

    void deletePostById(long id);

    void resetAutoIncrement(String tableName);
}
