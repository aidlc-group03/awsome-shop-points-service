package com.awsome.shop.point.application.api.dto.points;

import lombok.Data;

/**
 * 积分统计 DTO（供 Dashboard）
 */
@Data
public class PointsStatsDTO {

    private Long totalPointsIssued;
    private Long totalPointsUsed;
    private Integer activeAccounts;
}
