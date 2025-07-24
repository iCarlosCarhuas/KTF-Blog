package com.app.ktf.blog.service;

import com.app.ktf.blog.entity.security.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Optional<UsuarioEntity> findByCorreo(String correo);
    UsuarioEntity save(UsuarioEntity user);
    List<UsuarioEntity> findAll();
    Optional<UsuarioEntity> findById(Long id);
    void deleteById(Long id);
    Optional<UsuarioEntity> validateLogin(String correo, String contraseña);
    Optional<UsuarioEntity> findByPublicId(String publicId);

}
