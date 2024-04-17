package com.example.checkout.framework.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class CheckOutDto {
    private Long checkOutId;
    private Long memberId;
    private Long productId;
    private long amount;
    private String shippingAddress;
    private LocalDateTime createdAt;
}
