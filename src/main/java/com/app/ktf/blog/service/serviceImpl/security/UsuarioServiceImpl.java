package com.app.ktf.blog.service.serviceImpl.security;

import com.app.ktf.blog.entity.security.UsuarioEntity;
import com.app.ktf.blog.repository.security.UsuarioRepository;
import com.app.ktf.blog.service.security.UsuarioService;
import com.app.ktf.blog.service.serviceImpl.GenericServiceImpl;
import com.app.ktf.blog.util.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<UsuarioEntity,Long> implements UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<UsuarioEntity> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public UsuarioEntity save(UsuarioEntity user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(BCryptUtil.hash(user.getPassword()));
        }
        return usuarioRepository.save(user);
    }

    @Override
    public List<UsuarioEntity> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<UsuarioEntity> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<UsuarioEntity> validateLogin(String correo, String contraseña) {
        Optional<UsuarioEntity> userOpt = usuarioRepository.findByCorreo(correo);
        if (userOpt.isPresent()) {
            UsuarioEntity user = userOpt.get();
            if (BCryptUtil.match(contraseña, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<UsuarioEntity> findByPublicId(String publicId) {
        return usuarioRepository.findByPublicId(publicId);
    }

}
