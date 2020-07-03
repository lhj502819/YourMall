package com.yourmall.commom.web.handler;

import com.yourmall.commom.vo.CommonResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Description：全局统一返回处理器
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2020/7/3
 */
@ControllerAdvice
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {

    /**
     * 此方法为判断是否走 #beforeBodyWrite方法
     *
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        if(returnType.getMethod() == null){
            return false;
        }
        return returnType.getMethod().getReturnType() == CommonResult.class;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //如果已经是CommonResult类型，则直接返回
        if(body instanceof CommonResult)
            return body;
        return CommonResult.success(body);
    }
}
