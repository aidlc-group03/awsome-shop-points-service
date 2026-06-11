package com.awsome.shop.point.application.api.dto.points.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 删除积分规则请求
 */
@Data
public class RuleDeleteRequest {

    @NotNull(message = "规则ID不能为空")
    private Long id;

    /** Gateway 注入: 当前操作管理员ID */
    private String operatorId;
}
