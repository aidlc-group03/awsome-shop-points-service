package com.awsome.shop.point.domain.impl.service.points;

import com.awsome.shop.point.common.dto.PageResult;
import com.awsome.shop.point.common.enums.PointsErrorCode;
import com.awsome.shop.point.common.exception.BusinessException;
import com.awsome.shop.point.domain.model.points.PointsRuleEntity;
import com.awsome.shop.point.domain.service.points.PointsRuleDomainService;
import com.awsome.shop.point.repository.points.PointsRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 积分规则领域服务实现
 */
@Service
@RequiredArgsConstructor
public class PointsRuleDomainServiceImpl implements PointsRuleDomainService {

    private final PointsRuleRepository pointsRuleRepository;

    @Override
    public PointsRuleEntity getById(Long id) {
        PointsRuleEntity entity = pointsRuleRepository.getById(id);
        if (entity == null) {
            throw new BusinessException(PointsErrorCode.RULE_NOT_FOUND);
        }
        return entity;
    }

    @Override
    public PageResult<PointsRuleEntity> page(int page, int size) {
        return pointsRuleRepository.page(page, size);
    }

    @Override
    public PointsRuleEntity create(String name, String type, Integer value,
                                   String triggerCondition, String description) {
        PointsRuleEntity entity = new PointsRuleEntity();
        entity.setName(name);
        entity.setType(type);
        entity.setValue(value);
        entity.setTriggerCondition(triggerCondition);
        entity.setDescription(description);
        entity.setStatus(1);
        pointsRuleRepository.save(entity);
        return pointsRuleRepository.getById(entity.getId());
    }

    @Override
    public PointsRuleEntity update(Long id, String name, String type, Integer value,
                                   String triggerCondition, String description, Integer status) {
        PointsRuleEntity entity = getById(id);
        if (name != null) entity.setName(name);
        if (type != null) entity.setType(type);
        if (value != null) entity.setValue(value);
        if (triggerCondition != null) entity.setTriggerCondition(triggerCondition);
        if (description != null) entity.setDescription(description);
        if (status != null) entity.setStatus(status);
        pointsRuleRepository.update(entity);
        return pointsRuleRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        getById(id); // 确认存在
        pointsRuleRepository.deleteById(id);
    }
}
