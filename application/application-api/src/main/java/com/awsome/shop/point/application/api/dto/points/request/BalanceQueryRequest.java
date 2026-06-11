package com.awsome.shop.point.application.api.dto.points.request;

import lombok.Data;

/**
 * 积分余额查询请求
 *
 * <p>Gateway 注入 operatorId 作为当前登录用户的ID</p>
 */
@Data
public class BalanceQueryRequest {

    /** Gateway 注入: 当前登录用户ID */
    private String operatorId;
}
