package com.app.ktf.blog.service.serviceImpl.security;

import com.app.ktf.blog.entity.security.AccesoEntity;
import com.app.ktf.blog.repository.security.AccesoRepository;
import com.app.ktf.blog.service.security.AccesoService;
import com.app.ktf.blog.service.serviceImpl.GenericServiceImpl;

public class AccesoServiceImpl extends GenericServiceImpl<AccesoEntity, Long> implements AccesoService {
    private final AccesoRepository accesoRepository;
    public AccesoServiceImpl(AccesoRepository accesoRepository){
        super(accesoRepository);
        this.accesoRepository = accesoRepository;
    }
}
