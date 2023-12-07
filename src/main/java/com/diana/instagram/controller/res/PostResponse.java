package com.diana.instagram.controller.res;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PostResponse {

    private long id;

    private String description;

    private String media;

    private LocalDate postedDate;

    private boolean isArchived;


    public PostResponse() {

    }

    public PostResponse(long id, String description, String media, LocalDate postedDate) {
        this.id = id;
        this.description = description;
        this.media = media;
        this.postedDate = postedDate;
    }

    public PostResponse(String description, String media) {
        this.description = description;
        this.media = media;
    }
}
