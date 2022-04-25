package com.bbs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
