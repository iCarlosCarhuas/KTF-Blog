package com.app.ktf.blog.repository.security;

import com.app.ktf.blog.entity.security.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByCorreo(String correo);
    Optional<UsuarioEntity> findByCorreoAndContraseña(String correo, String contraseña);
    Optional<UsuarioEntity> findByPublicId(String publicId);
}
