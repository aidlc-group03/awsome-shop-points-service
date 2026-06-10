package com.awsome.shop.point.application.api.dto.points.request;

import com.awsome.shop.point.facade.http.request.common.GatewayInjectableRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * 交易记录查询请求
 */
@Data
public class TransactionQueryRequest implements GatewayInjectableRequest {

    @Min(value = 1, message = "页码最小为1")
    private Integer page = 1;

    @Min(value = 1, message = "每页大小最小为1")
    @Max(value = 100, message = "每页大小最大为100")
    private Integer size = 20;

    /** 交易类型筛选（可选） */
    private String type;

    // Gateway 注入字段
    private Long tenantId;
    private String traceId;
    private String userId;
}
