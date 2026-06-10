package com.awsome.shop.point.repository.points;

import com.awsome.shop.point.common.dto.PageResult;
import com.awsome.shop.point.domain.model.points.PointsRuleEntity;

/**
 * 积分规则仓储接口
 */
public interface PointsRuleRepository {

    PointsRuleEntity getById(Long id);

    PageResult<PointsRuleEntity> page(int page, int size);

    void save(PointsRuleEntity entity);

    void update(PointsRuleEntity entity);

    void deleteById(Long id);
}
