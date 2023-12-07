package com.diana.instagram.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Lob
    @Column(name = "MEDIA")
    private byte[] media;

    @Column(name = "POSTED_DATE")
    private LocalDate postedDate;

    @Column(name = "IS_ARCHIVED")
    private boolean isArchived;

    @ManyToOne
    @JoinColumn(name = "PROFILE_ID")
    private Profile profile;


    public Post() {

    }

    public Post(String description, byte[] media, Profile profile) {
        this.media = media;
        this.profile = profile;
        this.description = description;
        this.isArchived = false;
        this.postedDate = LocalDate.now();
    }

}


