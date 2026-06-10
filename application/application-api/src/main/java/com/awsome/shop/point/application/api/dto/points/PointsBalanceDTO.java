package com.awsome.shop.point.application.api.dto.points;

import lombok.Data;

/**
 * 积分余额 DTO
 */
@Data
public class PointsBalanceDTO {

    private Long userId;
    private Integer balance;
    private Integer totalEarned;
    private Integer totalUsed;
    private Integer redemptionCount;
}
