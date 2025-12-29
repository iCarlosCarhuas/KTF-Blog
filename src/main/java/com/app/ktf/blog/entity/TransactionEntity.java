package com.app.ktf.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class TransactionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sponsorName;

    // Amount shown to public (masked if needed in DTO)
    private BigDecimal amount;

    private String type; // DONATION, PAYOUT, SUBSCRIPTION

    private String status; // COMPLETED, PENDING

    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }
}
