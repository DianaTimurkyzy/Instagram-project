package com.diana.instagram.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "GENDER")
    private String gender;
    @Lob
    @Column(name = "PICTURE")
    private byte[] picture;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ROLES")
    private String roles;

    public Profile() {

    }

    public Profile(String username) {
        this.username = username;
    }


}
