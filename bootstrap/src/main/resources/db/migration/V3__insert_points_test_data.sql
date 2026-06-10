-- =============================================================
-- Points Service - 预置测试数据
-- =============================================================

-- 积分账户（对应 Auth 的测试用户）
INSERT INTO `points_account` (`user_id`, `balance`, `total_earned`, `total_used`, `redemption_count`) VALUES
(1, 10000, 10000, 0, 0),      -- admin
(2, 2580, 5000, 2420, 8),     -- employee (李明)
(3, 3200, 4000, 800, 3);      -- zhangsan (张三)

-- 积分交易记录（员工 user_id=2 的历史）
INSERT INTO `points_transaction` (`account_id`, `type`, `amount`, `balance_after`, `description`, `created_at`) VALUES
(2, 'PERFORMANCE', 2000, 2000, '2024年Q4绩效奖励', '2024-12-01 10:00:00'),
(2, 'SENIORITY', 1000, 3000, '入职3年工龄积分', '2025-01-01 00:00:00'),
(2, 'HOLIDAY', 1000, 4000, '春节福利积分', '2025-01-20 09:00:00'),
(2, 'SPECIAL', 1000, 5000, '优秀员工特别奖励', '2025-03-01 10:00:00'),
(2, 'REDEMPTION', -800, 4200, '兑换: Sony WH-1000XM5', '2025-03-15 14:30:00'),
(2, 'REDEMPTION', -500, 3700, '兑换: 小米手环8', '2025-04-01 11:00:00'),
(2, 'REDEMPTION', -1120, 2580, '兑换: 其他商品', '2025-05-10 16:00:00');

-- 积分交易记录（员工 user_id=3 的历史）
INSERT INTO `points_transaction` (`account_id`, `type`, `amount`, `balance_after`, `description`, `created_at`) VALUES
(3, 'PERFORMANCE', 2000, 2000, '2024年Q4绩效奖励', '2024-12-01 10:00:00'),
(3, 'HOLIDAY', 1000, 3000, '春节福利积分', '2025-01-20 09:00:00'),
(3, 'SPECIAL', 1000, 4000, '项目突出贡献奖励', '2025-02-15 10:00:00'),
(3, 'REDEMPTION', -800, 3200, '兑换: Apple AirPods Pro', '2025-04-10 15:00:00');

-- 积分规则
INSERT INTO `points_rule` (`name`, `type`, `value`, `trigger_condition`, `description`) VALUES
('季度绩效A', 'PERFORMANCE', 2000, '季度绩效评级为A', '季度绩效评级A获得2000积分'),
('季度绩效B', 'PERFORMANCE', 1000, '季度绩效评级为B', '季度绩效评级B获得1000积分'),
('入职周年', 'SENIORITY', 500, '每满一年', '每满一年入职周年奖励500积分'),
('春节福利', 'HOLIDAY', 1000, '春节', '春节全员发放1000积分'),
('中秋福利', 'HOLIDAY', 500, '中秋', '中秋全员发放500积分');
