package com.awsome.shop.point.application.api.dto.points.request;

import com.awsome.shop.point.facade.http.request.common.GatewayInjectableRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 删除积分规则请求
 */
@Data
public class RuleDeleteRequest implements GatewayInjectableRequest {

    @NotNull(message = "规则ID不能为空")
    private Long id;

    // Gateway 注入字段
    private Long tenantId;
    private String traceId;
    private String userId;
}
