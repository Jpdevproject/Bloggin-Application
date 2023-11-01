package com.codewithjyoti.blog.model;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Posts {
    private Long postId;
    private String title;
    private String content;
    private String imageName;
    private LocalDate createdDate;
}
