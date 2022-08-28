package com.example.blogapplication.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Using lombok annotations to get things done.
 * Using jpa notations to mark this class as an entity & create a table with a name & @uniqueConstraint as title.
 * Marking these fields as columns @Column
 * Making id field as primary key @Id
 * strategy = GenerationType.IDENTITY does is, it generates the primary key as per database identity column.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
        name = "Posts", uniqueConstraints = {@UniqueConstraint(columnNames = "title")}
)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "title", nullable = false)
    private String title;
}
