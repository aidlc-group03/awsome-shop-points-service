package com.awsome.shop.point.domain.service.points;

import com.awsome.shop.point.common.dto.PageResult;
import com.awsome.shop.point.domain.model.points.PointsAccountEntity;
import com.awsome.shop.point.domain.model.points.PointsTransactionEntity;

/**
 * 积分账户领域服务接口
 */
public interface PointsAccountDomainService {

    /**
     * 查询用户积分账户
     */
    PointsAccountEntity getByUserId(Long userId);

    /**
     * 扣减积分（兑换场景，由 Order Service 内部调用）
     *
     * @param userId      用户ID
     * @param amount      扣减金额（正数）
     * @param description 描述
     * @param orderId     关联订单ID
     * @return 扣减后余额
     */
    int deduct(Long userId, int amount, String description, Long orderId);

    /**
     * 退还积分（兑换失败补偿）
     *
     * @param userId      用户ID
     * @param amount      退还金额（正数）
     * @param description 描述
     * @param orderId     关联订单ID
     * @return 退还后余额
     */
    int refund(Long userId, int amount, String description, Long orderId);

    /**
     * 管理员调整积分
     *
     * @param userId      目标用户ID
     * @param amount      金额（正数=发放，负数=扣除）
     * @param type        交易类型
     * @param description 描述
     * @param operatorId  操作者ID
     */
    void adjust(Long userId, int amount, String type, String description, Long operatorId);

    /**
     * 查询用户交易记录
     */
    PageResult<PointsTransactionEntity> getTransactions(Long userId, String type, int page, int size);

    /**
     * 积分统计
     */
    long getTotalPointsIssued();

    long getTotalPointsUsed();

    int getActiveAccounts();
}
