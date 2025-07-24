package com.app.ktf.blog.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "profile")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    @Column(name = "user_image")
    private String userImage;

    private String username;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private Integer age;

}
