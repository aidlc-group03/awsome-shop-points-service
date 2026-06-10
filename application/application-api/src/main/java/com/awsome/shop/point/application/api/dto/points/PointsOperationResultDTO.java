package com.awsome.shop.point.application.api.dto.points;

import lombok.Data;

/**
 * 积分操作结果 DTO（内部 API 响应）
 */
@Data
public class PointsOperationResultDTO {

    private Boolean success;
    private Integer balance;
    private String message;

    public static PointsOperationResultDTO success(int balance) {
        PointsOperationResultDTO dto = new PointsOperationResultDTO();
        dto.setSuccess(true);
        dto.setBalance(balance);
        return dto;
    }

    public static PointsOperationResultDTO failure(String message) {
        PointsOperationResultDTO dto = new PointsOperationResultDTO();
        dto.setSuccess(false);
        dto.setMessage(message);
        return dto;
    }
}
