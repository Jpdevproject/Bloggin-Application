package com.codewithjyoti.blog.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "posts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostsEntity {
    @Id
    @Column(name = "post_id", nullable = false)
    private Long postId;
    private String title;
    private String content;
    private String imageName;
    private LocalDate createdDate;
    @OneToOne
    @JoinColumn(name = "category_id")
    private CategoriesEntity categoriesEntity;

}
