package com.app.ktf.blog.repository.security;

import com.app.ktf.blog.entity.security.AccesoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccesoRepository extends JpaRepository<AccesoEntity, Long> {
}
