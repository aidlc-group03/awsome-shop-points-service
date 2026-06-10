package com.awsome.shop.point.domain.model.points;

/**
 * 积分交易类型枚举
 */
public enum TransactionType {

    /** 兑换消费（负数） */
    REDEMPTION,

    /** 绩效奖励（正数） */
    PERFORMANCE,

    /** 工龄奖励（正数） */
    SENIORITY,

    /** 节日福利（正数） */
    HOLIDAY,

    /** 特别奖励（正数） */
    SPECIAL,

    /** 管理员发放（正数） */
    ADMIN_ADD,

    /** 管理员扣除（负数） */
    ADMIN_DEDUCT,

    /** 兑换退还（正数） */
    REFUND
}
