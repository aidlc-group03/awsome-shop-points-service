package com.awsome.shop.point.application.api.dto.points.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 编辑积分规则请求
 */
@Data
public class RuleUpdateRequest {

    @NotNull(message = "规则ID不能为空")
    private Long id;

    private String name;

    private String type;

    private Integer value;

    private String triggerCondition;

    private String description;

    /** 0=禁用, 1=启用 */
    private Integer status;

    /** Gateway 注入: 当前操作管理员ID */
    private String operatorId;
}
