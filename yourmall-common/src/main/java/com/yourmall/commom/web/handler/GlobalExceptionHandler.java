package com.yourmall.commom.web.handler;

import com.yourmall.commom.constant.SysErrorCodeEnum;
import com.yourmall.commom.exception.ServiceException;
import com.yourmall.commom.vo.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Description：全局异常处理
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2020/7/3
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理 ServiceException 异常
     */
    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult serviceExceptionHandler(HttpServletRequest request, ServiceException exception) {
        logger.debug("[serviceExceptionHandler]", exception);
        //包装CommonResult结果
        return CommonResult.error(exception.getCode(), exception.getMessage());
    }

    /**
     * 处理 {@link MissingServletRequestParameterException}
     *
     * SpringMVC 参数不正确
     */
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonResult missingServletRequestParameterExceptionHandler(HttpServletRequest request, MissingServletRequestParameterException exception) {
        logger.debug("[missingServletRequestParameterExceptionHandler]",exception);
        //包装CommonResult结果
        return CommonResult.error(SysErrorCodeEnum.MISSING_REQUEST_PARAM_ERROR.getCode(),SysErrorCodeEnum.MISSING_REQUEST_PARAM_ERROR.getMessage());
    }

    /**
     * 处理其他异常
     */
    public CommonResult exceptionHandler(HttpServletRequest request, Exception e){
        //记录异常日志
        logger.error("[exceptionHandler]",e);
        //返回Error CommonResult
        return CommonResult.error(SysErrorCodeEnum.SYS_ERROR.getCode(),SysErrorCodeEnum.SYS_ERROR.getMessage());
    }

}
