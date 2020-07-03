package com.yourmall.commom.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * Description：通用结果返回类
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2020/7/3
 */
public final class CommonResult<T> implements Serializable {
    private static final long serialVersionUID = 2108856171607215561L;

    public static Integer CODE_SUCCESS = 0;

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 将传入的 result 对象，转换成另外一个泛型结果的对象
     * <p>
     * 因为 A 方法返回的 CommonResult 对象，不满足调用 B 方法的返回，所以需要进行转换。
     *
     * @param result 传入的 result 对象
     * @param <T>    返回的泛型
     * @return 新的 CommonResult 对象
     */
    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.getCode(), result.getMessage());
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        Assert.isTrue(!CODE_SUCCESS.equals(code), "code必须是错误码");
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.message = message;
        return result;
    }

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = CODE_SUCCESS;
        result.data = data;
        result.message = "";
        return result;
    }

    @JsonIgnore //忽略 避免jackson序列化给前端
    public boolean isSuccess() {//方便判断是否成功
        return CODE_SUCCESS.equals(code);
    }

    @JsonIgnore //忽略 避免jackson序列化给前端
    public boolean isError() {//方便判断是否失败
        return !isSuccess();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
