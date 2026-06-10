package com.awsome.shop.point.application.api.dto.points.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 内部扣减积分请求（供 Order Service）
 */
@Data
public class DeductPointsRequest {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "金额不能为空")
    @Min(value = 1, message = "金额必须大于0")
    private Integer amount;

    @NotBlank(message = "描述不能为空")
    private String description;

    private Long orderId;
}
