package com.awsome.shop.point.application.api.dto.points;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 积分规则 DTO
 */
@Data
public class PointsRuleDTO {

    private Long id;
    private String name;
    private String type;
    private Integer value;
    private String triggerCondition;
    private String description;
    private Integer status;
    private LocalDateTime createdAt;
}
