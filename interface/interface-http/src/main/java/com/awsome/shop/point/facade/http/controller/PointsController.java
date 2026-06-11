package com.awsome.shop.point.facade.http.controller;

import com.awsome.shop.point.application.api.dto.points.PointsBalanceDTO;
import com.awsome.shop.point.application.api.dto.points.PointsTransactionDTO;
import com.awsome.shop.point.application.api.dto.points.request.BalanceQueryRequest;
import com.awsome.shop.point.application.api.dto.points.request.TransactionQueryRequest;
import com.awsome.shop.point.application.api.service.points.PointsApplicationService;
import com.awsome.shop.point.common.dto.PageResult;
import com.awsome.shop.point.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 员工积分 Controller
 */
@Tag(name = "Employee Points", description = "员工积分查询")
@RestController
@RequestMapping("/api/v1/point")
@RequiredArgsConstructor
public class PointsController {

    private final PointsApplicationService pointsApplicationService;

    @Operation(summary = "查询积分余额")
    @PostMapping("/balance")
    public Result<PointsBalanceDTO> getBalance(@RequestBody BalanceQueryRequest request) {
        Long userId = Long.parseLong(request.getOperatorId());
        return Result.success(pointsApplicationService.getBalance(userId));
    }

    @Operation(summary = "积分交易记录")
    @PostMapping("/transactions")
    public Result<PageResult<PointsTransactionDTO>> getTransactions(
            @RequestBody @Valid TransactionQueryRequest request) {
        Long userId = Long.parseLong(request.getOperatorId());
        return Result.success(pointsApplicationService.getTransactions(userId, request));
    }
}
