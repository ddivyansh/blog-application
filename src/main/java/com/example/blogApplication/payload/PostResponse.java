package com.example.blogApplication.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

//to avoid using getters, setters we're using @Data
@Data
@AllArgsConstructor
public class PostResponse {
    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
