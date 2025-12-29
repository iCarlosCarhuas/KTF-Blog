package com.app.ktf.blog.entity.security;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class RolEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    // Core Roles
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    public static final String ROLE_CREATOR = "CREATOR";
    public static final String ROLE_SPONSOR = "SPONSOR";

    // Committee Roles
    public static final String ROLE_PRESIDENTE = "PRESIDENTE";
    public static final String ROLE_TESORERO = "TESORERO";
    public static final String ROLE_SECRETARIO = "SECRETARIO";
    public static final String ROLE_VOCAL = "VOCAL";
    public static final String ROLE_FISCAL = "FISCAL";

    @Column(name = "role_route")
    private String roleRoute;

    @Column(name = "role_description")
    private String roleDescription;

    @OneToMany(mappedBy = "rol")
    private List<AccesoEntity> accesos;
}
