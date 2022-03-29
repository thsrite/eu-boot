package com.eu.core.exception;

/**
 * 全局异常状态码
 *
 * @author jiangxd
 */
public enum GlobalExceptionCode {

    /**
     * 请求成功
     */
    SUCCESS(0, "SUCCESS"),
    ERROR(1, "系统异常"),
    REQUEST_ARGUMENT_EXCEPTION(2, "请求参数异常"),
    USER_LOGIN_STATUS_EXCEPTION(3, "当前用户登陆信息未进行初始化"),
    NOT_FOUND_ENUM(4, "未找到对应枚举类型"),
    FEIGN_ERROR(5, "Feign 接口请求异常"),
    JEDIS_POOL_INIT_FAIL(6, "Jedis Pool 初始化失败"),
    HTTP_REQUEST_ERROR(7, "网络请求异常"),
    FILE_UPLOAD_ERROR(8, "文件上传失败"),
    FILE_NOT_FOUND(9, "文件为空"),
    DATA_IS_NULL(10, "数据是空的"),
    NECESSARY_DATA_IS_NULL(11, "某些必填数据为空"),
    ES_INDEX_CREATE_FAILED(12, "ES索引创建失败"),
    ES_QUERY_FAILED(13, "ES查询失败"),

    METHOD_NOT_SUPPORT(400, "请求类型错误"),
    NO_AUTHORIZED(401, "接口未授权"),
    ILLEGAL_REQUEST(402, "非法请求"),
    NOT_FOUND(404, "404找不到资源"),
    REPEAT_REQUEST(405, "重复请求"),
    SIGN_FAILED(406, "签名校验失败"),
    TEMP_VERIF_FAILED(407, "时间戳检验失败"),
    SIGN_NOT_FOUND(408, "请传入签名"),
    TEMP_NOT_FOUND(409, "请传入时间戳"),

    PAY_TYPE_FAILED(600, "支付类型错误"),
    PAY_FAILED(601, "支付失败"),

    PHONE_NUMBER_FORMAT_WRONG(1000, "手机号码格式错误"),
    ACCOUNT_IS_DISABLED(1001, "帐号已被禁用"),
    USERNAME_IS_NOT_FOUNT(1002, "帐号不存在"),
    PASSWORD_IS_WRONG(1003, "密码输入错误"),
    USERNAME_ALREADY_EXISTS(1004, "用户名已存在"),
    USER_LOGIN_FAILED(1005, "用户授权失败"),
    USER_UPDATE_FAILED(1006, "用户修改失败"),
    USER_DELETE_FAILED(1007, "用户删除失败"),
    ROLES_NOT_FOUND(1008, "获取用户角色失败"),
    PASSWORD_CHANGE_FAILED(1009, "密码更新失败"),
    ROLE_NAME_EXISTS(1010, "角色名已存在"),
    ROLE_MARK_EXISTS(1011, "角色标识已存在"),
    ROLE_ADD_FAILED(1012, "角色新增失败"),
    ROLE_EDIT_FAILED(1013, "角色修改失败"),
    ROLE_HAS_USER_FAILED(1014, "角色已被使用,无法删除"),
    ROLE_DEL_FAILED(1015, "角色删除失败"),
    UPLOAD_USER_ROLE_FAILED(1016, "用户角色关系存证失败"),
    ROLE_QUERY_FAILED(1017, "角色查询失败"),
    AUTHORITY_NOT_FOUND(1018, "权限不存在"),
    AUTHORITY_ALREADY_EXISTS(1019, "权限名已存在"),
    AUTHORITY_DEL_FAILED(1020, "权限删除失败"),
    AUTHORITY_STATUS_FAILED(1021, "权限启用禁用失败"),
    AUTHORITY_ALREADY_USERD(1022, "权限已被分配,无法删除"),
    ADMIN_CANOT_EDIT(1023, "超级管理员权限不允许修改"),
    REAL_INFO_FAILED(1024, "用户实名认证失败"),

    MESSAGE_SEND_ERROR(3001, "短信发送失败"),
    MESSAGE_SEND_FREQUENTLY(3002, "短信发送频繁"),
    MESSAGE_CODE_ERROE(3003, "短信验证码错误"),
    MESSAGE_CODE_NOTEXITS(3004, "请先发送验证码"),

    DEL_ERROR(4001, "逻辑删除失败"),

    USER_NOT_FOUND(5001, "用户不存在"),
    USER_PASSWORD_ERROR(5002, "用户密码错误"),
    USER_EXIST(5003, "用户已存在"),
    USER_CONFIM_FAILED(5004, "两次密码不一致"),
    USER_REGISTER_FAILED(5005, "用户注册失败"),
    REFRESH_NOT_FOUND(5006, "刷新token失效"),

    CLIENT_AUTHENTICATION_FAILED(6000, "客户端认证失败"),
    WEBSOCKET_AUTHENTICATION_FAILED(6001, "websocket认证失败"),
    WEBSOCKET_TOKEN_UNEXISTS(6002, "websocket连接缺少token"),
    WEBSOCKET_USERID_UNEXISTS(6003, "websocket连接缺少userId"),

    ALREADY_UF(7001, "已经是好友，无法重复添加"),
    APPLY_UF(7002, "好友申请中，请稍后"),
    APPLY_NOTEXITS(7003, "好友申请不存在"),

    GROUP_CREATE_ERROR(8001, "群组创建失败"),
    GROUP_NOTEXITS(8002, "群组信息不存在"),
    NOT_GOURP_USER(8003, "您不是群成员"),
    NO_PERMISSION(8004, "没有权限"),
    USER_HAS_BAN(8005, "您已被禁言"),
    RECALL_TIMEOUT(8006, "发送3分钟之内可撤回"),
    ALREADY_GROUP_USER(8007, "已是群成员"),
    BLANK_MSG_FORBIDEN(8008, "不可发送空白内容"),

    APPKEY_NOT_EXITS(9001, "appKey不存在"),
    APPSECRET_ERROR(9002, "appSecret错误"),
    APPKEY_EXPIRED(9003, "appKey已过期"),
    TENANT_NOT_FOUND(9004, "租户信息不存在"),

    LIVE_CODE_INVALID(10001, "直播推流码已失效，请重新获取"),
    LIVE_ALREADY_START(10002, "直播已开始，请勿重新推流"),
    LIVE_VIOLATION_WAIT(10003, "直播间违规，禁播30分钟"),
    LIVE_VIOLATION_FORBIDDEN(10004, "直播间违规次数过多，已封禁"),

    ;

    /**
     * 异常代码
     */
    private final int code;

    /**
     * 异常信息
     */
    private final String msg;


    GlobalExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return msg;
    }


    public static GlobalExceptionCode getByCode(int code) {

        GlobalExceptionCode[] values = GlobalExceptionCode.values();

        for (GlobalExceptionCode value : values) {
            if (value.getCode() == code) {
                return value;
            }
        }

        throw new GlobalException(GlobalExceptionCode.NOT_FOUND_ENUM, String.format("未找到 [%s] 对应的异常类型!", code));
    }

}
