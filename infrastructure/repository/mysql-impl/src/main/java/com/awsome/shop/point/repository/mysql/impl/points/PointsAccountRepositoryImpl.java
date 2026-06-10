package com.awsome.shop.point.repository.mysql.impl.points;

import com.awsome.shop.point.domain.model.points.PointsAccountEntity;
import com.awsome.shop.point.repository.mysql.mapper.points.PointsAccountMapper;
import com.awsome.shop.point.repository.mysql.po.points.PointsAccountPO;
import com.awsome.shop.point.repository.points.PointsAccountRepository;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 积分账户仓储实现
 */
@Repository
@RequiredArgsConstructor
public class PointsAccountRepositoryImpl implements PointsAccountRepository {

    private final PointsAccountMapper pointsAccountMapper;

    @Override
    public PointsAccountEntity getByUserId(Long userId) {
        LambdaQueryWrapper<PointsAccountPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PointsAccountPO::getUserId, userId);
        PointsAccountPO po = pointsAccountMapper.selectOne(wrapper);
        return po == null ? null : toEntity(po);
    }

    @Override
    public void save(PointsAccountEntity entity) {
        PointsAccountPO po = toPO(entity);
        pointsAccountMapper.insert(po);
        entity.setId(po.getId());
    }

    @Override
    public boolean update(PointsAccountEntity entity) {
        PointsAccountPO po = toPO(entity);
        int rows = pointsAccountMapper.updateById(po);
        return rows > 0;
    }

    @Override
    public int countActiveAccounts() {
        return pointsAccountMapper.countActiveAccounts();
    }

    @Override
    public long sumTotalEarned() {
        return pointsAccountMapper.sumTotalEarned();
    }

    @Override
    public long sumTotalUsed() {
        return pointsAccountMapper.sumTotalUsed();
    }

    private PointsAccountEntity toEntity(PointsAccountPO po) {
        PointsAccountEntity entity = new PointsAccountEntity();
        entity.setId(po.getId());
        entity.setUserId(po.getUserId());
        entity.setBalance(po.getBalance());
        entity.setTotalEarned(po.getTotalEarned());
        entity.setTotalUsed(po.getTotalUsed());
        entity.setRedemptionCount(po.getRedemptionCount());
        entity.setCreatedAt(po.getCreatedAt());
        entity.setUpdatedAt(po.getUpdatedAt());
        entity.setVersion(po.getVersion());
        return entity;
    }

    private PointsAccountPO toPO(PointsAccountEntity entity) {
        PointsAccountPO po = new PointsAccountPO();
        po.setId(entity.getId());
        po.setUserId(entity.getUserId());
        po.setBalance(entity.getBalance());
        po.setTotalEarned(entity.getTotalEarned());
        po.setTotalUsed(entity.getTotalUsed());
        po.setRedemptionCount(entity.getRedemptionCount());
        po.setVersion(entity.getVersion());
        return po;
    }
}
