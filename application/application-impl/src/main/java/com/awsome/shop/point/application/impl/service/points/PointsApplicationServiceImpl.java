package com.awsome.shop.point.application.impl.service.points;

import com.awsome.shop.point.application.api.dto.points.*;
import com.awsome.shop.point.application.api.dto.points.request.*;
import com.awsome.shop.point.application.api.service.points.PointsApplicationService;
import com.awsome.shop.point.common.dto.PageResult;
import com.awsome.shop.point.common.exception.BusinessException;
import com.awsome.shop.point.domain.model.points.PointsAccountEntity;
import com.awsome.shop.point.domain.model.points.PointsRuleEntity;
import com.awsome.shop.point.domain.model.points.PointsTransactionEntity;
import com.awsome.shop.point.domain.service.points.PointsAccountDomainService;
import com.awsome.shop.point.domain.service.points.PointsRuleDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 积分应用服务实现
 */
@Service
@RequiredArgsConstructor
public class PointsApplicationServiceImpl implements PointsApplicationService {

    private final PointsAccountDomainService pointsAccountDomainService;
    private final PointsRuleDomainService pointsRuleDomainService;

    // ==================== 员工端 ====================

    @Override
    public PointsBalanceDTO getBalance(Long userId) {
        PointsAccountEntity account = pointsAccountDomainService.getByUserId(userId);
        return toBalanceDTO(account);
    }

    @Override
    public PageResult<PointsTransactionDTO> getTransactions(Long userId, TransactionQueryRequest request) {
        PageResult<PointsTransactionEntity> page = pointsAccountDomainService.getTransactions(
                userId, request.getType(), request.getPage(), request.getSize());
        return page.convert(this::toTransactionDTO);
    }

    // ==================== 管理员 ====================

    @Override
    public void adjustPoints(AdjustPointsRequest request) {
        Long operatorId = request.getOperatorId() != null ? Long.parseLong(request.getOperatorId()) : null;
        pointsAccountDomainService.adjust(
                request.getUserId(),
                request.getAmount(),
                request.getType(),
                request.getDescription(),
                operatorId
        );
    }

    @Override
    public PageResult<PointsTransactionDTO> getUserHistory(UserHistoryRequest request) {
        PageResult<PointsTransactionEntity> page = pointsAccountDomainService.getTransactions(
                request.getUserId(), null, request.getPage(), request.getSize());
        return page.convert(this::toTransactionDTO);
    }

    @Override
    public PageResult<PointsRuleDTO> listRules(RuleListRequest request) {
        PageResult<PointsRuleEntity> page = pointsRuleDomainService.page(request.getPage(), request.getSize());
        return page.convert(this::toRuleDTO);
    }

    @Override
    public PointsRuleDTO createRule(RuleCreateRequest request) {
        PointsRuleEntity entity = pointsRuleDomainService.create(
                request.getName(), request.getType(), request.getValue(),
                request.getTriggerCondition(), request.getDescription());
        return toRuleDTO(entity);
    }

    @Override
    public PointsRuleDTO updateRule(RuleUpdateRequest request) {
        PointsRuleEntity entity = pointsRuleDomainService.update(
                request.getId(), request.getName(), request.getType(), request.getValue(),
                request.getTriggerCondition(), request.getDescription(), request.getStatus());
        return toRuleDTO(entity);
    }

    @Override
    public void deleteRule(RuleDeleteRequest request) {
        pointsRuleDomainService.delete(request.getId());
    }

    // ==================== 内部 API ====================

    @Override
    public PointsOperationResultDTO deductPoints(DeductPointsRequest request) {
        try {
            int balance = pointsAccountDomainService.deduct(
                    request.getUserId(), request.getAmount(),
                    request.getDescription(), request.getOrderId());
            return PointsOperationResultDTO.success(balance);
        } catch (BusinessException e) {
            return PointsOperationResultDTO.failure(e.getErrorMessage());
        }
    }

    @Override
    public PointsOperationResultDTO refundPoints(RefundPointsRequest request) {
        int balance = pointsAccountDomainService.refund(
                request.getUserId(), request.getAmount(),
                request.getDescription(), request.getOrderId());
        return PointsOperationResultDTO.success(balance);
    }

    @Override
    public PointsStatsDTO getStats() {
        PointsStatsDTO dto = new PointsStatsDTO();
        dto.setTotalPointsIssued(pointsAccountDomainService.getTotalPointsIssued());
        dto.setTotalPointsUsed(pointsAccountDomainService.getTotalPointsUsed());
        dto.setActiveAccounts(pointsAccountDomainService.getActiveAccounts());
        return dto;
    }

    // ==================== 转换方法 ====================

    private PointsBalanceDTO toBalanceDTO(PointsAccountEntity entity) {
        PointsBalanceDTO dto = new PointsBalanceDTO();
        dto.setUserId(entity.getUserId());
        dto.setBalance(entity.getBalance());
        dto.setTotalEarned(entity.getTotalEarned());
        dto.setTotalUsed(entity.getTotalUsed());
        dto.setRedemptionCount(entity.getRedemptionCount());
        return dto;
    }

    private PointsTransactionDTO toTransactionDTO(PointsTransactionEntity entity) {
        PointsTransactionDTO dto = new PointsTransactionDTO();
        dto.setId(entity.getId());
        dto.setType(entity.getType().name());
        dto.setAmount(entity.getAmount());
        dto.setBalanceAfter(entity.getBalanceAfter());
        dto.setDescription(entity.getDescription());
        dto.setOperatorId(entity.getOperatorId());
        dto.setRelatedOrderId(entity.getRelatedOrderId());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    private PointsRuleDTO toRuleDTO(PointsRuleEntity entity) {
        PointsRuleDTO dto = new PointsRuleDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setValue(entity.getValue());
        dto.setTriggerCondition(entity.getTriggerCondition());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
