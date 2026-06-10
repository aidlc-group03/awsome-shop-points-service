package com.awsome.shop.point.domain.model.points;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 积分账户领域实体
 */
@Data
public class PointsAccountEntity {

    private Long id;

    private Long userId;

    private Integer balance;

    private Integer totalEarned;

    private Integer totalUsed;

    private Integer redemptionCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer version;

    /**
     * 扣减积分（兑换场景）
     *
     * @param amount 扣减金额（正数）
     */
    public void deduct(int amount) {
        this.balance -= amount;
        this.totalUsed += amount;
        this.redemptionCount += 1;
    }

    /**
     * 退还积分（兑换失败补偿）
     *
     * @param amount 退还金额（正数）
     */
    public void refund(int amount) {
        this.balance += amount;
        this.totalUsed -= amount;
        this.redemptionCount -= 1;
    }

    /**
     * 增加积分（管理员发放 / 奖励）
     *
     * @param amount 增加金额（正数）
     */
    public void earn(int amount) {
        this.balance += amount;
        this.totalEarned += amount;
    }

    /**
     * 管理员扣除积分
     *
     * @param amount 扣除金额（正数）
     */
    public void adminDeduct(int amount) {
        this.balance -= amount;
        this.totalUsed += amount;
    }

    /**
     * 检查余额是否充足
     *
     * @param amount 需要的金额
     * @return true 如果余额充足
     */
    public boolean hasSufficientBalance(int amount) {
        return this.balance >= amount;
    }
}
