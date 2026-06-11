package com.awsome.shop.point.application.api.dto.points.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 创建积分规则请求
 */
@Data
public class RuleCreateRequest {

    @NotBlank(message = "规则名称不能为空")
    private String name;

    @NotBlank(message = "规则类型不能为空")
    private String type;

    @NotNull(message = "积分值不能为空")
    private Integer value;

    private String triggerCondition;

    private String description;

    /** Gateway 注入: 当前操作管理员ID */
    private String operatorId;
}
