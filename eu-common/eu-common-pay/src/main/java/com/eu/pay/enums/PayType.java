package com.eu.pay.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;

/**
 * 支付类型
 */
@Slf4j
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PayType {

    NOPAY(-1, "未支付"),
    ZFB(0, "支付宝支付"),
    WX(1, "微信支付"),
    QB(2, "钱包余额支付"),

    ;

    /**
     * 代码
     */
    private final int code;

    /**
     * 内容
     */
    private final String msg;


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    PayType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据代码获取枚举
     * 不要尝试缓存全部的枚举，该方法用到的频率不会太高，且枚举很少，不会造成资源浪费
     * <p>
     * 使用 @JsonCreator 让 jackson 解析 json 的时候能匹配到该枚举
     * 参考：https://segmentfault.com/q/1010000020636087
     *
     * @param code
     * @return
     */
    @JsonCreator
    public static PayType getByCode(int code) throws Exception {

        PayType[] values = PayType.values();

        for (PayType value : values) {
            if (value.getCode() == code) {
                return value;
            }
        }

        throw new Exception(String.format("未找到 [%s] 对应的支付类型!", code));
    }

    @JsonCreator
    public static PayType getByMsg(String msg) throws Exception {

        PayType[] values = PayType.values();

        for (PayType value : values) {
            if (value.getMsg().equals(msg)) {
                return value;
            }
        }

        throw new Exception(String.format("未找到 [%s] 对应的支付类型!", msg));
    }

}
