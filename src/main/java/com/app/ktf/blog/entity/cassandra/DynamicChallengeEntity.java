package com.app.ktf.blog.entity.cassandra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("dynamic_challenges")
public class DynamicChallengeEntity {

    @PrimaryKey
    private UUID challengeId;

    private String subject; // e.g. Mathematics, History

    private String level; // e.g. Primary, PhD

    private String questionContent;

    private List<String> options;

    private String correctAnswer;

    private Instant createdAt;

    private String generatedBy; // AI model or User ID
}
