package com.awsome.shop.point.application.api.dto.points.request;

import com.awsome.shop.point.facade.http.request.common.GatewayInjectableRequest;
import lombok.Data;

/**
 * 积分余额查询请求（userId 由 Gateway 注入）
 */
@Data
public class BalanceQueryRequest implements GatewayInjectableRequest {

    // Gateway 注入字段
    private Long tenantId;
    private String traceId;
    private String userId;
}
