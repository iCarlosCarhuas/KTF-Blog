package com.app.ktf.blog.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @Column(name = "user_image")
    private String userImage;

    private String username;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private LocalDate birthdate;

}
