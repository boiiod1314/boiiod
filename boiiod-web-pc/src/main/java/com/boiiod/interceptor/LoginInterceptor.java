package com.boiiod.interceptor;

import com.boiiod.consts.ConstRedisKeyEnum;
import com.boiiod.consts.ConstService;
import com.boiiod.controller.BaseController;
import com.boiiod.entity.User;
import com.boiiod.exception.BoiiodException;
import com.boiiod.param.result.UserContext;
import com.boiiod.service.UserService;
import com.boiiod.service.redis.RedisTemplate;
import com.boiiod.status.StatusCode;
import com.boiiod.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User:lc
 * Date:12/29/15 2:39 PM
 * Desc: PC端判断用户是否登录
 */
public class LoginInterceptor extends BaseController implements HandlerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(LoginInterceptor.class);
    private static final List<String> AUTO_LOGIN_URI_LIST = new ArrayList<>();  // 需要自动登录的地址
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    private List<String> commonExcludedUrls;
    private List<String> excludedUrls;

    static {
        AUTO_LOGIN_URI_LIST.add("/");
        AUTO_LOGIN_URI_LIST.add("/note/list.html");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String requestUri = request.getRequestURI();
        if (requestUri == null || AUTO_LOGIN_URI_LIST.contains(requestUri)) {
            return checkLogin(requestUri);
        }
        /**
         * 静态数据依赖于接口. 所以, 可以直接过滤
         */
        if (commonExcludedUrls != null && !commonExcludedUrls.isEmpty()) {
            for (String excludeUrl : commonExcludedUrls) {
                if (requestUri.endsWith(excludeUrl)) return true;
            }
        }
        if (excludedUrls != null && !excludedUrls.isEmpty()) {
            for (String excludeUrl : excludedUrls) {
                if (requestUri.endsWith(excludeUrl)) return true;
            }
        }

        checkLogin(null);

        /*if (userContextObj == null) {
            String head = request.getHeader("X-Requested-With");
            if (head == null) {// 若不是ajax请求值为null
                LOG.debug("拦截器：从session中获取的UserContext为空, 不通过, 跳转到登录页面, 请求路径: {}", requestUri);
                String path = request.getContextPath();
                String basePath = new StringBuffer().append(request.getScheme()).append("://").
                        append(request.getServerName()).append(":").append(request.getServerPort()).
                        append(path).append("/").toString();
                response.sendRedirect(basePath + "users/login.html");
                return false;
            } else {
                LOG.debug("拦截器：从session中获取的UserContext为空, 不通过, 请求路径: {}", requestUri);
                throw new ZouFanqiException(StatusCode.NOT_LOGIN);
            }
        }*/
        return true;
    }

    private boolean checkLogin(String requestUri) throws BoiiodException {
        /**
         * 需要登录
         */

        /**
         * 检验cookie中的token
         */
        String token = this.getCookie(ConstService.COOKIE_LOGIN_TOKEN);
        UserContext uc = (UserContext) this.request.getSession().getAttribute(ConstService.SESSION_LOGIN_USER);
        if (StringUtil.isEmpty(token)) {
            if (uc == null && requestUri == null) {
                throw new BoiiodException(StatusCode.NOT_LOGIN);
            } else {
                this.updateCookieAndSessionInfo(uc);
                return true;
            }
        }
        /**
         * 有token, 可直接登录, 若无UserContext数据, 则补数据
         */
        if (uc == null) {
            String userId = this.redisTemplate.hget(ConstRedisKeyEnum.MAP_TOKEN_USER_ID.name(), token);
            if (StringUtil.isEmpty(userId)) {
                if (requestUri == null) throw new BoiiodException(StatusCode.NOT_LOGIN);
                return true;
            }
            User user = null;//this.userService.getById(Long.valueOf(userId));
            this.buildUCAndSetSessionData(user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }



    public void setCommonExcludedUrls(List<String> commonExcludedUrls) {
        this.commonExcludedUrls = commonExcludedUrls;
    }

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }
}
