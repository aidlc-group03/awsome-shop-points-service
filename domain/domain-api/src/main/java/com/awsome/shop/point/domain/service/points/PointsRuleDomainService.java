package com.awsome.shop.point.domain.service.points;

import com.awsome.shop.point.common.dto.PageResult;
import com.awsome.shop.point.domain.model.points.PointsRuleEntity;

/**
 * 积分规则领域服务接口
 */
public interface PointsRuleDomainService {

    PointsRuleEntity getById(Long id);

    PageResult<PointsRuleEntity> page(int page, int size);

    PointsRuleEntity create(String name, String type, Integer value,
                            String triggerCondition, String description);

    PointsRuleEntity update(Long id, String name, String type, Integer value,
                            String triggerCondition, String description, Integer status);

    void delete(Long id);
}
