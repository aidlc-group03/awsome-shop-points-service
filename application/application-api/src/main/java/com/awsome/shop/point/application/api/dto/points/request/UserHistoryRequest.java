package com.awsome.shop.point.application.api.dto.points.request;

import com.awsome.shop.point.facade.http.request.common.GatewayInjectableRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理员查看用户积分历史请求
 */
@Data
public class UserHistoryRequest implements GatewayInjectableRequest {

    @NotNull(message = "用户ID不能为空")
    private Long targetUserId;

    @Min(value = 1, message = "页码最小为1")
    private Integer page = 1;

    @Min(value = 1, message = "每页大小最小为1")
    @Max(value = 100, message = "每页大小最大为100")
    private Integer size = 20;

    // Gateway 注入字段
    private Long tenantId;
    private String traceId;
    private String userId;
}
