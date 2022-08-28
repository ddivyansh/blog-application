package com.example.blogApplication.utils;

import com.example.blogApplication.payload.PostDto;


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
