package com.awsome.shop.point.application.api.dto.points.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * 积分规则列表请求
 */
@Data
public class RuleListRequest {

    @Min(value = 1, message = "页码最小为1")
    private Integer page = 1;

    @Min(value = 1, message = "每页大小最小为1")
    @Max(value = 100, message = "每页大小最大为100")
    private Integer size = 10;

    /** Gateway 注入: 当前操作管理员ID */
    private String operatorId;
}
