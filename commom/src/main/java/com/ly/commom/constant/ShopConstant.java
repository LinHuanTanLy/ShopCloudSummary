package com.ly.commom.constant;

public class ShopConstant {
    public static final String REDIS_PRODUCT_KEY = "session_redis_key_";
    // session key名
    public static final String SESSION_KEY_NAME = "user";
    // 版本号
    public static final Integer VERSION = 1;

    /**
     * 用户状态：0：正常 1：锁定   2：禁用
     */
    public interface userStatus {
        int NORMAL = 0;
        int LOCKED = 1;
        int DISABLE = 2;
    }

    /**
     * 删除标记0正常1删除
     */
    public interface isDelete {
        int DELETE = 1;
        int NORMAL = 0;
    }

    /**
     * 订单状态 0-未支付 1-已支付 2-已取消 3-已退款
     */
    public interface OrderStatus {
        int NOT_PAYED = 0;
        int IS_PAYED = 1;
        int IS_CANCELED = 2;
        int REFUNDED = 3;
    }

    /**
     * 订单筛选规则
     * 每次定时器都会去扫描
     */
    public interface OrderMaxTimeOut {
        double MAX_TIME_OUT = 1;
    }
}
