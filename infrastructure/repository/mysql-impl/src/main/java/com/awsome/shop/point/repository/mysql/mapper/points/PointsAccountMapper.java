package com.awsome.shop.point.repository.mysql.mapper.points;

import com.awsome.shop.point.repository.mysql.po.points.PointsAccountPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 积分账户 Mapper 接口
 */
@Mapper
public interface PointsAccountMapper extends BaseMapper<PointsAccountPO> {

    @Select("SELECT COUNT(*) FROM points_account WHERE balance > 0")
    int countActiveAccounts();

    @Select("SELECT COALESCE(SUM(total_earned), 0) FROM points_account")
    long sumTotalEarned();

    @Select("SELECT COALESCE(SUM(total_used), 0) FROM points_account")
    long sumTotalUsed();
}
