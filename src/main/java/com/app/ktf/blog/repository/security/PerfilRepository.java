package com.app.ktf.blog.repository.security;

import com.app.ktf.blog.entity.security.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {
}
