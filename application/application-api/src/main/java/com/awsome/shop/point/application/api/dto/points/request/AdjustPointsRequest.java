package com.awsome.shop.point.application.api.dto.points.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理员调整积分请求
 *
 * <p>OpenAPI 字段: userId, amount, type, description</p>
 * <p>Gateway 注入字段: operatorId（当前操作的管理员ID）</p>
 */
@Data
public class AdjustPointsRequest {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "金额不能为空")
    private Integer amount;

    @NotBlank(message = "类型不能为空")
    private String type;

    @NotBlank(message = "描述不能为空")
    private String description;

    /** Gateway 注入: 当前操作管理员ID */
    private String operatorId;
}
