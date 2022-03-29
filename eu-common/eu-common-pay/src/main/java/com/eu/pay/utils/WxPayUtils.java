package com.eu.pay.utils;

import com.ijpay.core.enums.SignType;
import com.ijpay.core.kit.WxPayKit;

import java.util.HashMap;
import java.util.Map;

public class WxPayUtils {

    public static Map<String, String> appPrepayIdCreateSign(String appId, String partnerId, String prepayId, String partnerKey, String nonceStr, SignType signType) {
        Map<String, String> packageParams = new HashMap<>(8);
        packageParams.put("appid", appId);
        packageParams.put("partnerid", partnerId);
        packageParams.put("prepayid", prepayId);
        packageParams.put("package", "Sign=WXPay");
        packageParams.put("noncestr", nonceStr);
        packageParams.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));

        if (signType == null) {
            signType = SignType.MD5;
        }
        String packageSign = WxPayKit.createSign(packageParams, partnerKey, signType);
        packageParams.put("sign", packageSign);
        return packageParams;
    }


    /**
     * <p>小程序-预付订单再次签名</p>
     * <p>注意此处签名方式需与统一下单的签名类型一致</p>
     *
     * @param appId      应用编号
     * @param prepayId   预付订单号
     * @param partnerKey API Key
     * @param signType   签名方式
     * @return 再次签名后的 Map
     */
    public static Map<String, String> miniAppPrepayIdCreateSign(String appId, String prepayId, String partnerKey, String nonceStr, SignType signType) {
        Map<String, String> packageParams = new HashMap<>(6);
        packageParams.put("appId", appId);
        packageParams.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
        packageParams.put("nonceStr", nonceStr);
        packageParams.put("package", "prepay_id=" + prepayId);

        if (signType == null) {
            signType = SignType.MD5;
        }
        packageParams.put("signType", signType.getType());
        String packageSign = WxPayKit.createSign(packageParams, partnerKey, signType);
        packageParams.put("paySign", packageSign);
        return packageParams;
    }

}
