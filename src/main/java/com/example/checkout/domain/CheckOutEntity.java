package com.example.checkout.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "CHECKOUT_TABLE")
public class CheckOutEntity {
    @Id @GeneratedValue
    private Long checkOutId;

    private Long memberId;

    private Long productId;

    private long amount;

    private String shippingAddress;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
