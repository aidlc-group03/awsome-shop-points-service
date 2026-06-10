package com.awsome.shop.point.application.api.dto.points;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 积分交易记录 DTO
 */
@Data
public class PointsTransactionDTO {

    private Long id;
    private String type;
    private Integer amount;
    private Integer balanceAfter;
    private String description;
    private Long operatorId;
    private Long relatedOrderId;
    private LocalDateTime createdAt;
}
