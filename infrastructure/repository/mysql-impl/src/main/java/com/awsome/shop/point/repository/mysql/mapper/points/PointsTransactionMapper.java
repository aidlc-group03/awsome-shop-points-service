package com.awsome.shop.point.repository.mysql.mapper.points;

import com.awsome.shop.point.repository.mysql.po.points.PointsTransactionPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 积分交易记录 Mapper 接口
 */
@Mapper
public interface PointsTransactionMapper extends BaseMapper<PointsTransactionPO> {

    /**
     * 分页查询某账户的交易记录
     */
    IPage<PointsTransactionPO> selectPageByAccountId(IPage<PointsTransactionPO> page,
                                                      @Param("accountId") Long accountId,
                                                      @Param("type") String type);
}
