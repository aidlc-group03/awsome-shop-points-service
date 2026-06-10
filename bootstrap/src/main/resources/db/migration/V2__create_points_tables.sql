-- =============================================================
-- Points Service - 积分业务表
-- =============================================================

-- 积分账户表
CREATE TABLE `points_account` (
    `id`               BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`          BIGINT       NOT NULL COMMENT '关联用户ID',
    `balance`          INT          NOT NULL DEFAULT 0 COMMENT '当前可用余额',
    `total_earned`     INT          NOT NULL DEFAULT 0 COMMENT '累计获得',
    `total_used`       INT          NOT NULL DEFAULT 0 COMMENT '累计使用',
    `redemption_count` INT          NOT NULL DEFAULT 0 COMMENT '兑换次数',
    `created_at`       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `version`          INT          NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='积分账户表';

-- 积分交易记录表
CREATE TABLE `points_transaction` (
    `id`               BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `account_id`       BIGINT       NOT NULL COMMENT '积分账户ID',
    `type`             VARCHAR(20)  NOT NULL COMMENT '交易类型',
    `amount`           INT          NOT NULL COMMENT '金额（正=获得,负=支出）',
    `balance_after`    INT          NOT NULL COMMENT '交易后余额',
    `description`      VARCHAR(200) DEFAULT NULL COMMENT '交易说明',
    `operator_id`      BIGINT       DEFAULT NULL COMMENT '操作者ID（管理员调整时记录）',
    `related_order_id` BIGINT       DEFAULT NULL COMMENT '关联订单ID（兑换时记录）',
    `created_at`       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易时间',
    PRIMARY KEY (`id`),
    KEY `idx_account_id` (`account_id`),
    KEY `idx_type` (`type`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='积分交易记录表';

-- 积分规则表
CREATE TABLE `points_rule` (
    `id`                BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`              VARCHAR(100) NOT NULL COMMENT '规则名称',
    `type`              VARCHAR(20)  NOT NULL COMMENT '规则类型',
    `value`             INT          NOT NULL COMMENT '积分值',
    `trigger_condition` VARCHAR(500) DEFAULT NULL COMMENT '触发条件描述',
    `description`       VARCHAR(500) DEFAULT NULL COMMENT '规则说明',
    `status`            INT          NOT NULL DEFAULT 1 COMMENT '状态: 0=禁用, 1=启用',
    `created_at`        DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`        DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `created_by`        BIGINT       DEFAULT NULL COMMENT '创建者',
    `updated_by`        BIGINT       DEFAULT NULL COMMENT '更新者',
    `deleted`           TINYINT      NOT NULL DEFAULT 0 COMMENT '逻辑删除 0-未删除 1-已删除',
    `version`           INT          NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='积分规则表';
