package com.awsome.shop.point.repository.points;

import com.awsome.shop.point.common.dto.PageResult;
import com.awsome.shop.point.domain.model.points.PointsTransactionEntity;

/**
 * 积分交易记录仓储接口
 */
public interface PointsTransactionRepository {

    /**
     * 保存交易记录
     */
    void save(PointsTransactionEntity entity);

    /**
     * 分页查询某账户的交易记录
     *
     * @param accountId 账户ID
     * @param type      交易类型（可为 null，null 表示不过滤）
     * @param page      页码
     * @param size      每页大小
     */
    PageResult<PointsTransactionEntity> pageByAccountId(Long accountId, String type, int page, int size);
}
