package com.codewithjyoti.blog.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String about;
}
