package com.awsome.shop.point.application.api.dto.points.request;

import com.awsome.shop.point.facade.http.request.common.GatewayInjectableRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 创建积分规则请求
 */
@Data
public class RuleCreateRequest implements GatewayInjectableRequest {

    @NotBlank(message = "规则名称不能为空")
    private String name;

    @NotBlank(message = "规则类型不能为空")
    private String type;

    @NotNull(message = "积分值不能为空")
    private Integer value;

    private String triggerCondition;

    private String description;

    // Gateway 注入字段
    private Long tenantId;
    private String traceId;
    private String userId;
}
