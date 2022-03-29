package com.eu.redis.constants;

/**
 * redis key维护
 *
 * @author jiangxd
 */
public class RedisKey {

    /**
     * 分布式锁
     * 发送短信验证码使用
     * arg1: 短信验证码类型 code
     * arg2: 电话号码
     */
    public static final String LOCK_SMS_CAPTCHA = "IM-LOCK-SMS-CAPTCHA-%s-%s";

    /**
     * reids lock
     */
    public static final String LOCK_KEY = "EU-LOCK-KEY:";

    /**
     * 短信验证码发送标识
     * 用于作为一分钟倒计时的标识
     * arg1: 短信验证码类型 code
     * arg2: 电话号码
     */
    public static final String KEY_SMS_CAPTCHA_SEND_MARK = "IM-KEY-SMS-CAPTCHA-SEND-MARK-%s-%s";

    /**
     * 某手机号某种类型的短信验证码的 key
     * arg1: 短信验证码类型 code
     * arg2: 电话号码
     */
    public static final String KEY_SMS_CAPTCHA = "IM-KEY-SMS-CAPTCHA-%s-%s";

    /**
     * 某手机号某种类型的短信验证码的 key
     */
    public static final String KEY_PHONE_CAPTCHA = "IM-KEY-PHONE-CAPTCHA:";

    /**
     * 用户token
     */
    public static final String TOKEN_PREFIX = "IM-USER-TOKEN-%s";

    /**
     * 接口签名key
     */
    public static final String KEY_API_SIGN = "IM-KEY_API_SIGN";

}
