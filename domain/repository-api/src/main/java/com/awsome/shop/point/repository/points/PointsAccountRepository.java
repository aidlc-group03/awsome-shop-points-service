package com.awsome.shop.point.repository.points;

import com.awsome.shop.point.domain.model.points.PointsAccountEntity;

/**
 * 积分账户仓储接口
 */
public interface PointsAccountRepository {

    /**
     * 根据用户ID查询积分账户
     */
    PointsAccountEntity getByUserId(Long userId);

    /**
     * 保存新账户
     */
    void save(PointsAccountEntity entity);

    /**
     * 更新账户（乐观锁）
     *
     * @return 是否更新成功（乐观锁失败返回 false）
     */
    boolean update(PointsAccountEntity entity);

    /**
     * 统计活跃账户数
     */
    int countActiveAccounts();

    /**
     * 统计总发放积分
     */
    long sumTotalEarned();

    /**
     * 统计总使用积分
     */
    long sumTotalUsed();
}
