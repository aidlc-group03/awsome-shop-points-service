package com.awsome.shop.point.facade.http.controller;

import com.awsome.shop.point.application.api.dto.points.PointsOperationResultDTO;
import com.awsome.shop.point.application.api.dto.points.PointsStatsDTO;
import com.awsome.shop.point.application.api.dto.points.request.DeductPointsRequest;
import com.awsome.shop.point.application.api.dto.points.request.RefundPointsRequest;
import com.awsome.shop.point.application.api.service.points.PointsApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 内部积分 API Controller（供 Order Service 调用）
 */
@Tag(name = "Internal", description = "服务间内部接口")
@RestController
@RequestMapping("/api/v1/internal/points")
@RequiredArgsConstructor
public class InternalPointsController {

    private final PointsApplicationService pointsApplicationService;

    @Operation(summary = "扣减积分")
    @PostMapping("/deduct")
    public PointsOperationResultDTO deductPoints(@RequestBody @Valid DeductPointsRequest request) {
        return pointsApplicationService.deductPoints(request);
    }

    @Operation(summary = "退还积分")
    @PostMapping("/refund")
    public PointsOperationResultDTO refundPoints(@RequestBody @Valid RefundPointsRequest request) {
        return pointsApplicationService.refundPoints(request);
    }

    @Operation(summary = "积分统计")
    @GetMapping("/stats")
    public PointsStatsDTO getStats() {
        return pointsApplicationService.getStats();
    }
}
