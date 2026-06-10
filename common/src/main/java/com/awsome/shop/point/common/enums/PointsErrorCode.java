package com.awsome.shop.point.common.enums;

/**
 * 积分业务错误码
 *
 * <p>错误码前缀决定 HTTP 状态码映射：</p>
 * <ul>
 *   <li>BIZ_xxx → 200 OK（业务异常，通过响应体中的code区分）</li>
 *   <li>NOT_FOUND_xxx → 404 Not Found</li>
 * </ul>
 */
public enum PointsErrorCode implements ErrorCode {

    /**
     * 积分余额不足
     */
    INSUFFICIENT_BALANCE("BIZ_001", "积分余额不足"),

    /**
     * 积分账户不存在
     */
    ACCOUNT_NOT_FOUND("NOT_FOUND_002", "积分账户不存在"),

    /**
     * 积分规则不存在
     */
    RULE_NOT_FOUND("NOT_FOUND_003", "积分规则不存在"),

    /**
     * 调整金额无效
     */
    INVALID_AMOUNT("BIZ_002", "调整金额不能为零");

    private final String code;
    private final String message;

    PointsErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
