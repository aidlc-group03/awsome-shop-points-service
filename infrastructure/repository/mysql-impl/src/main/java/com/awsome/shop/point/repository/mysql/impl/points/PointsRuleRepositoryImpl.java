package com.awsome.shop.point.repository.mysql.impl.points;

import com.awsome.shop.point.common.dto.PageResult;
import com.awsome.shop.point.domain.model.points.PointsRuleEntity;
import com.awsome.shop.point.repository.mysql.mapper.points.PointsRuleMapper;
import com.awsome.shop.point.repository.mysql.po.points.PointsRulePO;
import com.awsome.shop.point.repository.points.PointsRuleRepository;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

/**
 * 积分规则仓储实现
 */
@Repository
@RequiredArgsConstructor
public class PointsRuleRepositoryImpl implements PointsRuleRepository {

    private final PointsRuleMapper pointsRuleMapper;

    @Override
    public PointsRuleEntity getById(Long id) {
        PointsRulePO po = pointsRuleMapper.selectById(id);
        return po == null ? null : toEntity(po);
    }

    @Override
    public PageResult<PointsRuleEntity> page(int page, int size) {
        LambdaQueryWrapper<PointsRulePO> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(PointsRulePO::getCreatedAt);
        IPage<PointsRulePO> result = pointsRuleMapper.selectPage(new Page<>(page, size), wrapper);

        PageResult<PointsRuleEntity> pageResult = new PageResult<>();
        pageResult.setCurrent(result.getCurrent());
        pageResult.setSize(result.getSize());
        pageResult.setTotal(result.getTotal());
        pageResult.setPages(result.getPages());
        pageResult.setRecords(result.getRecords().stream().map(this::toEntity).collect(Collectors.toList()));
        return pageResult;
    }

    @Override
    public void save(PointsRuleEntity entity) {
        PointsRulePO po = toPO(entity);
        pointsRuleMapper.insert(po);
        entity.setId(po.getId());
    }

    @Override
    public void update(PointsRuleEntity entity) {
        PointsRulePO po = toPO(entity);
        pointsRuleMapper.updateById(po);
    }

    @Override
    public void deleteById(Long id) {
        pointsRuleMapper.deleteById(id);
    }

    private PointsRuleEntity toEntity(PointsRulePO po) {
        PointsRuleEntity entity = new PointsRuleEntity();
        entity.setId(po.getId());
        entity.setName(po.getName());
        entity.setType(po.getType());
        entity.setValue(po.getValue());
        entity.setTriggerCondition(po.getTriggerCondition());
        entity.setDescription(po.getDescription());
        entity.setStatus(po.getStatus());
        entity.setCreatedAt(po.getCreatedAt());
        entity.setUpdatedAt(po.getUpdatedAt());
        entity.setCreatedBy(po.getCreatedBy());
        entity.setUpdatedBy(po.getUpdatedBy());
        entity.setVersion(po.getVersion());
        return entity;
    }

    private PointsRulePO toPO(PointsRuleEntity entity) {
        PointsRulePO po = new PointsRulePO();
        po.setId(entity.getId());
        po.setName(entity.getName());
        po.setType(entity.getType());
        po.setValue(entity.getValue());
        po.setTriggerCondition(entity.getTriggerCondition());
        po.setDescription(entity.getDescription());
        po.setStatus(entity.getStatus());
        po.setVersion(entity.getVersion());
        return po;
    }
}
