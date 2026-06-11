package com.awsome.shop.point.application.api.dto.points.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * 交易记录查询请求
 *
 * <p>Gateway 注入 operatorId 作为当前登录用户的ID</p>
 */
@Data
public class TransactionQueryRequest {

    @Min(value = 1, message = "页码最小为1")
    private Integer page = 1;

    @Min(value = 1, message = "每页大小最小为1")
    @Max(value = 100, message = "每页大小最大为100")
    private Integer size = 20;

    /** 交易类型筛选（可选） */
    private String type;

    /** Gateway 注入: 当前登录用户ID */
    private String operatorId;
}
