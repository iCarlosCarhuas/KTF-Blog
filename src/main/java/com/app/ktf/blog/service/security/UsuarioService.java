package com.app.ktf.blog.service.security;

import com.app.ktf.blog.entity.security.UsuarioEntity;
import com.app.ktf.blog.service.GenericService;

import java.util.List;
import java.util.Optional;

public interface UsuarioService extends GenericService<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByCorreo(String correo);
    UsuarioEntity save(UsuarioEntity user);
    List<UsuarioEntity> findAll();
    Optional<UsuarioEntity> findById(Long id);
    void deleteById(Long id);
    Optional<UsuarioEntity> validateLogin(String correo, String contraseña);
    Optional<UsuarioEntity> findByPublicId(String publicId);

}
