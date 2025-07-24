package com.app.ktf.blog.entity.security;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "access")
public class AccesoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private PerfilEntity perfil;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RolEntity rol;
}
