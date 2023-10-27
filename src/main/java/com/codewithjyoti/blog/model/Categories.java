package com.codewithjyoti.blog.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Categories {

    private Long categoryId;
    private String title;
    private String description;
}
