package com.awsome.shop.point.domain.impl.service.points;

import com.awsome.shop.point.common.dto.PageResult;
import com.awsome.shop.point.common.enums.PointsErrorCode;
import com.awsome.shop.point.common.exception.BusinessException;
import com.awsome.shop.point.domain.model.points.PointsAccountEntity;
import com.awsome.shop.point.domain.model.points.PointsTransactionEntity;
import com.awsome.shop.point.domain.model.points.TransactionType;
import com.awsome.shop.point.domain.service.points.PointsAccountDomainService;
import com.awsome.shop.point.repository.points.PointsAccountRepository;
import com.awsome.shop.point.repository.points.PointsTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 积分账户领域服务实现
 */
@Service
@RequiredArgsConstructor
public class PointsAccountDomainServiceImpl implements PointsAccountDomainService {

    private final PointsAccountRepository pointsAccountRepository;
    private final PointsTransactionRepository pointsTransactionRepository;

    @Override
    public PointsAccountEntity getByUserId(Long userId) {
        PointsAccountEntity account = pointsAccountRepository.getByUserId(userId);
        if (account == null) {
            throw new BusinessException(PointsErrorCode.ACCOUNT_NOT_FOUND);
        }
        return account;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deduct(Long userId, int amount, String description, Long orderId) {
        PointsAccountEntity account = getByUserId(userId);

        if (!account.hasSufficientBalance(amount)) {
            throw new BusinessException(PointsErrorCode.INSUFFICIENT_BALANCE);
        }

        account.deduct(amount);
        boolean updated = pointsAccountRepository.update(account);
        if (!updated) {
            throw new BusinessException(PointsErrorCode.INSUFFICIENT_BALANCE, "并发操作冲突，请重试");
        }

        // 创建交易记录
        PointsTransactionEntity transaction = PointsTransactionEntity.create(
                account.getId(), TransactionType.REDEMPTION,
                -amount, account.getBalance(),
                description, null, orderId
        );
        pointsTransactionRepository.save(transaction);

        return account.getBalance();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int refund(Long userId, int amount, String description, Long orderId) {
        PointsAccountEntity account = getByUserId(userId);

        account.refund(amount);
        boolean updated = pointsAccountRepository.update(account);
        if (!updated) {
            // 退还操作重试（补偿必须成功）
            account = getByUserId(userId);
            account.refund(amount);
            pointsAccountRepository.update(account);
        }

        // 创建交易记录
        PointsTransactionEntity transaction = PointsTransactionEntity.create(
                account.getId(), TransactionType.REFUND,
                amount, account.getBalance(),
                description, null, orderId
        );
        pointsTransactionRepository.save(transaction);

        return account.getBalance();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void adjust(Long userId, int amount, String type, String description, Long operatorId) {
        if (amount == 0) {
            throw new BusinessException(PointsErrorCode.INVALID_AMOUNT);
        }

        // 获取或创建账户
        PointsAccountEntity account = pointsAccountRepository.getByUserId(userId);
        if (account == null) {
            account = new PointsAccountEntity();
            account.setUserId(userId);
            account.setBalance(0);
            account.setTotalEarned(0);
            account.setTotalUsed(0);
            account.setRedemptionCount(0);
            account.setVersion(0);
            pointsAccountRepository.save(account);
        }

        TransactionType transactionType;
        if (amount > 0) {
            account.earn(amount);
            transactionType = TransactionType.ADMIN_ADD;
        } else {
            int absAmount = Math.abs(amount);
            if (!account.hasSufficientBalance(absAmount)) {
                throw new BusinessException(PointsErrorCode.INSUFFICIENT_BALANCE);
            }
            account.adminDeduct(absAmount);
            transactionType = TransactionType.ADMIN_DEDUCT;
        }

        pointsAccountRepository.update(account);

        // 创建交易记录
        PointsTransactionEntity transaction = PointsTransactionEntity.create(
                account.getId(), transactionType,
                amount, account.getBalance(),
                description, operatorId, null
        );
        pointsTransactionRepository.save(transaction);
    }

    @Override
    public PageResult<PointsTransactionEntity> getTransactions(Long userId, String type, int page, int size) {
        PointsAccountEntity account = getByUserId(userId);
        return pointsTransactionRepository.pageByAccountId(account.getId(), type, page, size);
    }

    @Override
    public long getTotalPointsIssued() {
        return pointsAccountRepository.sumTotalEarned();
    }

    @Override
    public long getTotalPointsUsed() {
        return pointsAccountRepository.sumTotalUsed();
    }

    @Override
    public int getActiveAccounts() {
        return pointsAccountRepository.countActiveAccounts();
    }
}
