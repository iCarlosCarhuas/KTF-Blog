package com.app.ktf.blog.service.serviceImpl.security;

import com.app.ktf.blog.entity.security.PerfilEntity;
import com.app.ktf.blog.repository.security.PerfilRepository;
import com.app.ktf.blog.service.security.PerfilService;
import com.app.ktf.blog.service.serviceImpl.GenericServiceImpl;

public class PerfilServiceImpl extends GenericServiceImpl<PerfilEntity,Long> implements PerfilService {

    private final PerfilRepository perfilRepository;
    public PerfilServiceImpl(PerfilRepository perfilRepository){
        super(perfilRepository);
        this.perfilRepository = perfilRepository;
    }
}
