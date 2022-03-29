package com.eu.core.handle;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.eu.core.annotation.PreAuthorize;
import com.eu.core.exception.GlobalException;
import com.eu.core.exception.GlobalExceptionCode;
import com.eu.core.filter.RepeatedlyRequestWrapper;
import com.eu.core.thread.CurrentUser;
import com.eu.core.utils.JWTUtil;
import com.eu.core.utils.SignUtil;
import com.eu.core.utils.http.HttpHelper;
import com.eu.core.wrapper.GlobalResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局请求拦截器
 *
 * @author jiangxd
 */
@Slf4j
@Component
public class GlobalRequestHandler implements HandlerInterceptor {

    /**
     * 所有权限标识
     */
    private static final String ALL_PERMISSION = "*:*:*";

    /**
     * 管理员角色权限标识
     */
    private static final String SUPER_ADMIN = "admin";

    /**
     * JWT Token 在请求头中的 KEY
     */
    @Value("${jwt.key}")
    private String jwtKey;

    /**
     * JWT Token 加密签名
     */
    @Value("${jwt.sign}")
    private String jwtSign;

    /**
     * 是否启用接口授权
     */
    @Value("${security.authority}")
    private boolean isAuthority;

    /**
     * 是否启用接口签名校验
     */
    @Value("${security.sign}")
    private boolean sign;

    /**
     * 时间戳有效期
     */
    @Value("${security.tstimeout}")
    private int tstimeout;

    /**
     * 签名有效期
     */
    @Value("${security.signtimeout}")
    private int signtimeout;

//    @Autowired
//    private RedissonClient redissonClient;


    /**
     * 请求前置拦截
     *
     * @param request
     * @param response
     * @param handler
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        // 处理跨域所产生的 OPETIONS 请求
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return Boolean.TRUE;
        }

        // 从请求头中获取前端通过 header 传递过来的 token
        String jwtToken = request.getHeader(this.jwtKey);

        // 但是只要存在 TOKEN 则必须进行解析并保证 TOKEN 正常
        if (StrUtil.isNotBlank(jwtToken)) {
            JWTUtil.JWT jwt = JWTUtil.INSTANCE.check(jwtToken, this.jwtSign);
            if (jwt.getStatus() == JWTUtil.JWT.NORMAL) {     //TOKEN 解析正常

                // 配置文件开启接口授权
                if (this.isAuthority) {
                    // 判断类方法是否有权限注解，如果没有则跳过授权
                    PreAuthorize annotation = null;
                    if (handler instanceof HandlerMethod) {
                        annotation = ((HandlerMethod) handler).getMethodAnnotation(PreAuthorize.class);
                    }

                    // 类方法有权限注解，需要判断用户是否有该权限
                    if (null != annotation && !this.hasPermi(annotation.value(), jwt)) {
                        // 接口未授权
                        this.unAuthorized(request, response);
                        return Boolean.FALSE;
                    }
                }

                // 请求签名校验
                if (this.sign) {
                    this.vaildSign(request);
                }

                CurrentUser.init(Long.parseLong(jwt.getUserId()), jwt.getUsername());
                // 若本次请求合法，记录本次请求的各项参数及请求人
                log.info("REQUEST:[{}:{}][{}:{}]", request.getMethod(), request.getRequestURI(), CurrentUser.getId(), CurrentUser.getUsername());
                return Boolean.TRUE;
            }
        }

        // 其它情况, 如不存在 token 或 token 解析异常的均提示未授权
        this.unAuthorized(request, response);
        return Boolean.FALSE;

    }

    /**
     * 未授权response
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void unAuthorized(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONUtil.toJsonStr(new GlobalResponseWrapper(GlobalExceptionCode.NO_AUTHORIZED)));
        writer.close();
        log.error("[{}:{}]401", request.getMethod(), request.getRequestURI());
    }

    /**
     * 请求相应拦截
     * 不论接口内部是否发生异常，相应都会进入该方法
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        // 请求结束，记录相应日志
        log.info("RESPONSE:[{}:{}][{}:{}]", request.getMethod(), request.getRequestURI(), CurrentUser.getId(), CurrentUser.getUsername());
        //当前请求结束需要销毁线程中存储的内容，否则线程池的作用会导致这些缓存的数据无法被虚拟机销毁
        CurrentUser.destroy();
    }

    /**
     * 判断用户是否有该接口权限
     *
     * @param perm 接口类方法的权限标识
     * @param jwt  用户权限
     */
    private boolean hasPermi(String perm, JWTUtil.JWT jwt) {
        // 接口权限标识
        String[] userAuthoritys = perm.split(",");

        // 从jwt中取出用户权限标识，判断用户是否有该接口的访问权限
        for (String userAuthority : userAuthoritys) {
            // 用户有权限且有该接口权限标识，则允许访问
            if (jwt.getAuthoritys().contains(ALL_PERMISSION) || (CollUtil.isNotEmpty(jwt.getAuthoritys()) && jwt.getAuthoritys().contains(userAuthority))) {
                return Boolean.TRUE;
            }
        }
        // 接口有权限，但是该用户没有权限 返回401未授权
        return Boolean.FALSE;
    }

    /**
     * 接口参数签名校验
     * header上新增两个参数
     * ts：当前时间戳(13位)  sign：客户端签名（签名规则如下）
     * <p>
     * 1.获取query and from-data and body参数转为key-value
     * 2.参数排序，按照key首字母升序 （加上ts）
     * 3.参数转为a=1&b=2形式
     * 4.步骤3得到的参数串 + 自定义密钥  进行md5加密
     * 5.将md5加密后的结果转为16进制大写
     *
     * @param request
     */
    public void vaildSign(HttpServletRequest request) {

        try {
            // 获取客户端时间戳、并校验
            Object clientTs = request.getHeader("ts");
            if (null == clientTs) {
                throw new GlobalException(GlobalExceptionCode.ILLEGAL_REQUEST, "请传入时间戳ts");
            }
            log.info("客户端时间戳==》【{}】", clientTs);

            // 获取取客户端签名
            Object clientSign = request.getHeader("sign");
            if (null == clientSign) {
                throw new GlobalException(GlobalExceptionCode.ILLEGAL_REQUEST, "请传入签名sign");
            }
            // 签名校验
            log.info("客户端签名==》【{}】", clientSign);

            this.vaildTimetamp(Convert.toLong(clientTs));
            log.info("时间戳校验通过");

            // 参数转为key-value
            Map<String, Object> paramMap = this.getParam(request);

            // 服务端签名==》参数排序->md5盐加密->转为16进制大写
            String serverSign = SignUtil.signTopRequest(paramMap, paramMap.get("salt") != null ? String.valueOf(paramMap.get("salt")) : "");
            log.info("服务端签名==》【{}】", serverSign);
            if (!serverSign.equals(Convert.toStr(clientSign))) {
                throw new GlobalException(GlobalExceptionCode.SIGN_FAILED);
            } else {

                // setCache 存储签名
//                RSetCache<Object> signSet = this.redissonClient.getSetCache(RedisKey.KEY_API_SIGN);
//                // 添加监听事件==》过期则删除
//                signSet.addListener((ExpiredObjectListener) name -> signSet.remove(serverSign));
//
//                // 从redis中获取签名,若存在，则说明重复请求
//                if (signSet.contains(serverSign)) {
//                    throw new GlobalException(GlobalExceptionCode.REPEAT_REQUEST);
//                } else {
//                    // 不存在，则把签名缓存到redis，且设置过期时间
//                    signSet.add(serverSign, signtimeout, TimeUnit.MILLISECONDS);
//                }
            }
        } catch (GlobalException e) {
            throw e;
        } catch (Exception e) {
            throw new GlobalException(GlobalExceptionCode.SIGN_FAILED);
        }

    }

    /**
     * 校验客户端时间戳是否合法
     *
     * @param timetamp 客户端时间戳
     */
    public void vaildTimetamp(Long timetamp) {
        // 服务端时间戳
        long currentTimeMillis = System.currentTimeMillis();
        // 服务端时间戳 - 客户端时间戳 》= 5分钟有效期
        log.info("服务端时间戳==》【{}】,客户端时间戳==》【{}】,时间差值==》【{}】s", currentTimeMillis, timetamp, (currentTimeMillis - timetamp) / 1000);
        if (currentTimeMillis - timetamp >= this.tstimeout || timetamp - currentTimeMillis >= this.tstimeout) {
            throw new GlobalException(GlobalExceptionCode.ILLEGAL_REQUEST);
        }
    }

    /**
     * 获取请求参数
     * 先body后query
     *
     * @param request
     */
    public Map<String, Object> getParam(HttpServletRequest request) {
        Map<String, Object> paramMap = new HashMap<>();

        // 获取body参数
        if (request instanceof RepeatedlyRequestWrapper) {
            RepeatedlyRequestWrapper repeatedlyRequest = (RepeatedlyRequestWrapper) request;
            String bodyString = HttpHelper.getBodyString(repeatedlyRequest);
            if (StrUtil.isNotBlank(bodyString)) {
                paramMap = JSONUtil.toBean(bodyString, HashMap.class);
            }
        }

        // 获取query参数
//        if (MapUtil.isEmpty(paramMap)) {

        // 请求参数
        String queryString = request.getQueryString();
        String[] split = queryString.split("&");

        //参数转为map结构
        for (String s : split) {
            String[] paramSpilt = s.split("=");
            paramMap.put(paramSpilt[0], paramSpilt.length == 2 ? paramSpilt[1] : "");
        }
//        }

        return paramMap;
    }

}
