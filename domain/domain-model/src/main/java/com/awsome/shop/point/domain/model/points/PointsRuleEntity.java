package com.awsome.shop.point.domain.model.points;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 积分规则领域实体
 */
@Data
public class PointsRuleEntity {

    private Long id;

    private String name;

    private String type;

    private Integer value;

    private String triggerCondition;

    private String description;

    /** 0=禁用, 1=启用 */
    private Integer status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long createdBy;

    private Long updatedBy;

    private Integer version;
}
