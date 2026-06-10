package com.awsome.shop.point.domain.model.points;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 积分交易记录领域实体
 */
@Data
public class PointsTransactionEntity {

    private Long id;

    private Long accountId;

    private TransactionType type;

    /** 金额（正=获得, 负=支出） */
    private Integer amount;

    /** 交易后余额 */
    private Integer balanceAfter;

    private String description;

    /** 操作者ID（管理员调整时记录） */
    private Long operatorId;

    /** 关联订单ID（兑换时记录） */
    private Long relatedOrderId;

    private LocalDateTime createdAt;

    /**
     * 创建交易记录的工厂方法
     */
    public static PointsTransactionEntity create(Long accountId, TransactionType type,
                                                  int amount, int balanceAfter,
                                                  String description, Long operatorId,
                                                  Long relatedOrderId) {
        PointsTransactionEntity entity = new PointsTransactionEntity();
        entity.setAccountId(accountId);
        entity.setType(type);
        entity.setAmount(amount);
        entity.setBalanceAfter(balanceAfter);
        entity.setDescription(description);
        entity.setOperatorId(operatorId);
        entity.setRelatedOrderId(relatedOrderId);
        return entity;
    }
}
