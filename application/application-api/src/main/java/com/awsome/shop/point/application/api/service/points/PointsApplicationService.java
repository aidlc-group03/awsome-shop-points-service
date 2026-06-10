package com.awsome.shop.point.application.api.service.points;

import com.awsome.shop.point.application.api.dto.points.*;
import com.awsome.shop.point.application.api.dto.points.request.*;
import com.awsome.shop.point.common.dto.PageResult;

/**
 * 积分应用服务接口
 */
public interface PointsApplicationService {

    // ==================== 员工端 ====================

    /**
     * 查询积分余额
     */
    PointsBalanceDTO getBalance(Long userId);

    /**
     * 查询交易记录
     */
    PageResult<PointsTransactionDTO> getTransactions(Long userId, TransactionQueryRequest request);

    // ==================== 管理员 ====================

    /**
     * 调整积分（发放/扣除）
     */
    void adjustPoints(AdjustPointsRequest request);

    /**
     * 查看用户积分历史
     */
    PageResult<PointsTransactionDTO> getUserHistory(UserHistoryRequest request);

    /**
     * 积分规则列表
     */
    PageResult<PointsRuleDTO> listRules(RuleListRequest request);

    /**
     * 创建积分规则
     */
    PointsRuleDTO createRule(RuleCreateRequest request);

    /**
     * 编辑积分规则
     */
    PointsRuleDTO updateRule(RuleUpdateRequest request);

    /**
     * 删除积分规则
     */
    void deleteRule(RuleDeleteRequest request);

    // ==================== 内部 API ====================

    /**
     * 扣减积分（供 Order Service）
     */
    PointsOperationResultDTO deductPoints(DeductPointsRequest request);

    /**
     * 退还积分（兑换失败补偿）
     */
    PointsOperationResultDTO refundPoints(RefundPointsRequest request);

    /**
     * 积分统计（供 Dashboard）
     */
    PointsStatsDTO getStats();
}
