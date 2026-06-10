package com.awsome.shop.point.application.api.dto.points.request;

import com.awsome.shop.point.facade.http.request.common.GatewayInjectableRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 编辑积分规则请求
 */
@Data
public class RuleUpdateRequest implements GatewayInjectableRequest {

    @NotNull(message = "规则ID不能为空")
    private Long id;

    private String name;

    private String type;

    private Integer value;

    private String triggerCondition;

    private String description;

    /** 0=禁用, 1=启用 */
    private Integer status;

    // Gateway 注入字段
    private Long tenantId;
    private String traceId;
    private String userId;
}
