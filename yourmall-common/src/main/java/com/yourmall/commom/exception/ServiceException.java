package com.yourmall.commom.exception;

/**
 * Description：服务异常
 *
 * 参考 https://www.kancloud.cn/onebase/ob/484204 文章
 * 一共 10 位，分成四段
 * 第一段，1 位，类型
 *      1 - 业务级别异常
 *      2 - 系统级别异常
 * 第二段，3 位，系统类型
 *      001 - 用户系统
 *      002 - 商品系统
 *      003 - 订单系统
 *      004 - 支付系统
 *      005 - 优惠劵系统
 *      ... - ...
 * 第三段，3 位，模块
 *      不限制规则。
 *      一般建议，每个系统里面，可能有多个模块，可以再去做分段。以用户系统为例子：
 *          001 - OAuth2 模块
 *          002 - User 模块
 *          003 - MobileCode 模块
 * 第四段，3 位，错误码
 *       不限制规则。
 *       一般建议，每个模块自增。
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2020/7/3
 */
public final class ServiceException extends RuntimeException{
    /**
     * Service异常的时候（用户名已经存在、商品库存不足等），选用的方案有；
     * 1、封装同一个的业务异常类 ServiceException，里面有错误码和错误提示，然后进行throws抛出
     * 2、封装通用的返回类{@link com.yourmall.commom.vo.CommonResult}，里面有错误码和错误提示，然后进行 return 返回。
     *
     * 但是
     * 1、Spring @Transactional 声明式事务，是基于异常进行回滚的，如果使用CommonResult返回，则事务回滚非常麻烦。
     * 2、当调用别人的方法时，如果别人返回的是CommonResult对象，还需要不断的进行判断，写起来挺麻烦的。
     *
     * 因此使用抛出业务异常ServiceException的方式
     */

    /**
     * 错误码
     */
    private final Integer code;

    public ServiceException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
