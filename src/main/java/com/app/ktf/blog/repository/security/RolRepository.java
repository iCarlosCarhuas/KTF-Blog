package com.app.ktf.blog.repository.security;

import com.app.ktf.blog.entity.security.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long> {
}
