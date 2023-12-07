package com.diana.instagram.controller.res;

import lombok.Data;

import java.util.List;

@Data
public class ProfileResponse {

    private long profileId;

    private String username;

    private String name;

    private String description;

    private String gender;

    private String picture;

    private List<PostResponse> posts;

}
