package com.codewithjyoti.blog.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Categories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CategoriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;
    private String title;
    private String description;

}
