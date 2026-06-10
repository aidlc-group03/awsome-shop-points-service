package com.awsome.shop.point.facade.http.controller;

import com.awsome.shop.point.application.api.dto.points.PointsRuleDTO;
import com.awsome.shop.point.application.api.dto.points.PointsTransactionDTO;
import com.awsome.shop.point.application.api.dto.points.request.*;
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
 * 管理员积分管理 Controller
 */
@Tag(name = "Admin Points", description = "管理员积分管理")
@RestController
@RequestMapping("/api/v1/admin/points")
@RequiredArgsConstructor
public class AdminPointsController {

    private final PointsApplicationService pointsApplicationService;

    @Operation(summary = "调整用户积分（发放/扣除）")
    @PostMapping("/adjust")
    public Result<Void> adjustPoints(@RequestBody @Valid AdjustPointsRequest request) {
        pointsApplicationService.adjustPoints(request);
        return Result.success();
    }

    @Operation(summary = "查看用户积分历史")
    @PostMapping("/user-history")
    public Result<PageResult<PointsTransactionDTO>> getUserHistory(
            @RequestBody @Valid UserHistoryRequest request) {
        return Result.success(pointsApplicationService.getUserHistory(request));
    }

    @Operation(summary = "积分规则列表")
    @PostMapping("/rule/list")
    public Result<PageResult<PointsRuleDTO>> listRules(@RequestBody @Valid RuleListRequest request) {
        return Result.success(pointsApplicationService.listRules(request));
    }

    @Operation(summary = "创建积分规则")
    @PostMapping("/rule/create")
    public Result<PointsRuleDTO> createRule(@RequestBody @Valid RuleCreateRequest request) {
        return Result.success(pointsApplicationService.createRule(request));
    }

    @Operation(summary = "编辑积分规则")
    @PostMapping("/rule/update")
    public Result<PointsRuleDTO> updateRule(@RequestBody @Valid RuleUpdateRequest request) {
        return Result.success(pointsApplicationService.updateRule(request));
    }

    @Operation(summary = "删除积分规则")
    @PostMapping("/rule/delete")
    public Result<Void> deleteRule(@RequestBody @Valid RuleDeleteRequest request) {
        pointsApplicationService.deleteRule(request);
        return Result.success();
    }
}
