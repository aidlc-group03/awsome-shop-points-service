package com.awsome.shop.point.application.api.dto.points.request;

import com.awsome.shop.point.facade.http.request.common.GatewayInjectableRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理员调整积分请求
 */
@Data
public class AdjustPointsRequest implements GatewayInjectableRequest {

    @NotNull(message = "用户ID不能为空")
    private Long targetUserId;

    @NotNull(message = "金额不能为空")
    private Integer amount;

    @NotBlank(message = "类型不能为空")
    private String type;

    @NotBlank(message = "描述不能为空")
    private String description;

    // Gateway 注入字段
    private Long tenantId;
    private String traceId;
    private String userId;
}
