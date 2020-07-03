package com.yourmall.commom.constant;

/**
 * Description：错误码枚举类
 *
 * 系统级异常，使用 2-001-000-000 段
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2020/7/3
 */
public enum SysErrorCodeEnum {
    SYS_ERROR(2001001000,"服务端发生异常"),
    MISSING_REQUEST_PARAM_ERROR(2001001001,"参数缺失"),
    VALIDATION_REQUEST_PARAM_ERROR(2001001001,"参数校验不正确")
    ;

    private final int code;

    private final String message;

    SysErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
