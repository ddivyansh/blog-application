package com.example.blogApplication.payload;

import lombok.Data;

/**
 *  Creating a DTO (data transfer object)object, DTO is a design pattern which is used to create a data object for transferring data from client to server or vice-versa.
 *  We don't simply return the entity object, as it's not a good practice.
 * @Data to reduce the boilerplate code.
 */
@Data
public class PostDto {
    private long id;
    private String description;
    private String title;
    private String content;
}
