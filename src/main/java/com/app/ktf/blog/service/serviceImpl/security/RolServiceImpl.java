package com.app.ktf.blog.service.serviceImpl.security;

import com.app.ktf.blog.entity.security.RolEntity;
import com.app.ktf.blog.repository.security.RolRepository;
import com.app.ktf.blog.service.security.RolService;
import com.app.ktf.blog.service.serviceImpl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class RolServiceImpl extends GenericServiceImpl<RolEntity,Long> implements RolService {
    @Autowired
    private final RolRepository rolRepository;
    public RolServiceImpl(RolRepository rolRepository) {
        super(rolRepository);
        this.rolRepository = rolRepository;
    }
}
