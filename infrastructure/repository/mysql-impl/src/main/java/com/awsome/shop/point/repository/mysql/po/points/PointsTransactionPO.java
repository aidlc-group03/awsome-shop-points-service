package com.awsome.shop.point.repository.mysql.po.points;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 积分交易记录持久化对象
 */
@Data
@TableName("points_transaction")
public class PointsTransactionPO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long accountId;

    private String type;

    private Integer amount;

    private Integer balanceAfter;

    private String description;

    private Long operatorId;

    private Long relatedOrderId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
