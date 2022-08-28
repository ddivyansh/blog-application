package com.example.blogapplication.utils;

import com.example.blogapplication.payload.PostDto;


import java.util.List;

public class PostsDtoList {
    private List<PostDto> postDtoList;

    public PostsDtoList(List<PostDto> postDtoList) {
        this.postDtoList = postDtoList;
    }

    public List<PostDto> getPostDtoList() {
        return postDtoList;
    }
}
