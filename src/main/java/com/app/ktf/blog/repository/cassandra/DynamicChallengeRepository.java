package com.app.ktf.blog.repository.cassandra;

import com.app.ktf.blog.entity.cassandra.DynamicChallengeEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DynamicChallengeRepository extends CassandraRepository<DynamicChallengeEntity, UUID> {
}
