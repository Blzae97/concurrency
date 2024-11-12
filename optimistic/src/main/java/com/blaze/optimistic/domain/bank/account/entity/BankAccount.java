package com.blaze.optimistic.domain.bank.account.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Table(name = "bank_account")
@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_account_id", columnDefinition = "int(11)")
    private Long bankAccountId;

    @Version
    @Column(name = "version", columnDefinition = "bigint(20)")
    private Long version;

    @Column(name = "name", columnDefinition = "varchar(20)")
    private String name;

    @Column(name = "number", columnDefinition = "varchar(20)")
    private String number;

    @Column(name = "balance", columnDefinition = "decimal(19,2)")
    private BigDecimal balance;

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "timestamp(3)")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "timestamp(3)")
    private LocalDateTime updatedAt;

    @Builder
    public BankAccount(
            Long bankAccountId,
            Long version,
            String name,
            String number,
            BigDecimal balance,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.bankAccountId = bankAccountId;
        this.version = version;
        this.name = name;
        this.number = number;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void updateBalance(BigDecimal money) {
        this.balance = money;
    }
}
