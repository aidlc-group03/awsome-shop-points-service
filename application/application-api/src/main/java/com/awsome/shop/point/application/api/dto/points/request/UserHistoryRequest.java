package com.awsome.shop.point.application.api.dto.points.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理员查看用户积分历史请求
 *
 * <p>OpenAPI 字段: userId, page, size</p>
 * <p>Gateway 注入字段: operatorId（当前操作的管理员ID）</p>
 */
@Data
public class UserHistoryRequest {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Min(value = 1, message = "页码最小为1")
    private Integer page = 1;

    @Min(value = 1, message = "每页大小最小为1")
    @Max(value = 100, message = "每页大小最大为100")
    private Integer size = 20;

    /** Gateway 注入: 当前操作管理员ID */
    private String operatorId;
}
