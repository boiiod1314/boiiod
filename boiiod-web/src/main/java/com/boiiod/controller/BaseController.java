package com.boiiod.controller;

import com.boiiod.consts.ConstRedisKeyEnum;
import com.boiiod.consts.ConstService;
import com.boiiod.entity.User;
import com.boiiod.exception.BoiiodException;
import com.boiiod.param.result.UserContext;
import com.boiiod.service.redis.RedisTemplate;
import com.boiiod.status.StatusCode;
import com.boiiod.utils.ExceptionUtil;
import com.boiiod.utils.StringUtil;
import com.boiiod.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by boiiod on 16/10/26.
 */
public class BaseController {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected RedisTemplate redisTemplate;

    /**
     * 是否已登录
     *
     * @return
     *
     * @throws BoiiodException
     */
    protected boolean isLogin() throws BoiiodException {
        UserContext user = this.justGetUserContext();
        return user != null;
    }

    protected Integer getUserId() throws BoiiodException {
        User user;
        UserContext uc = this.getUserContext();
        if (uc == null || (user = uc.getUser()) == null) {
            throw new BoiiodException(StatusCode.NOT_LOGIN);
        }
        return user.getId();
    }

    protected Integer justGetUserId() throws BoiiodException {
        UserContext uc = this.justGetUserContext();
        User user;
        if (uc == null || (user = uc.getUser()) == null) return null;
        return user.getId();
    }

    protected UserContext getUserContext() throws BoiiodException {
        UserContext user = this.justGetUserContext();
        if (user == null) {
            throw new BoiiodException(StatusCode.NOT_LOGIN);
        }
        return user;
    }

    protected UserContext justGetUserContext() throws BoiiodException {
        UserContext user = (UserContext) request.getSession().getAttribute(ConstService.SESSION_LOGIN_USER);
        return user;
    }

    protected void clearCookieAndSessionInfo() throws BoiiodException {
        clearCookieAndSessionInfo(null);
    }

    protected void clearCookieAndSessionInfo(String userId) throws BoiiodException {
        if (StringUtil.isEmpty(userId)) {
            Integer uId = this.justGetUserId();
            if (StringUtil.isNotAutoId(uId)) return;
            userId = uId.toString();
        }
        this.request.getSession().invalidate();
        String token = this.redisTemplate.hget(ConstRedisKeyEnum.MAP_USER_ID_TOKEN.name(), userId);
        if (StringUtil.isNotEmpty(token))
            this.redisTemplate.hdel(ConstRedisKeyEnum.MAP_TOKEN_USER_ID.name(), token);
        this.redisTemplate.hdel(ConstRedisKeyEnum.MAP_USER_ID_TOKEN.name(), userId);
    }

    protected UserContext buildUCAndSetSessionData(Object... ucPart) throws BoiiodException {
        if (ucPart == null || ucPart.length == 0) return null;

        UserContext uc = new UserContext();

        for (Object u : ucPart) {
            if (u == null) continue;
            if (u.getClass().equals(User.class)) {
                uc.setUser((User) u);
            } // else if...
        }
        if (uc.getUser() == null) return null;
        /**
         * 添加登录信息到缓存
         */
        updateCookieAndSessionInfo(uc);
        return uc;
    }

    protected void updateCookieAndSessionInfo(UserContext uc) throws BoiiodException {
        User user;
        if (uc == null || (user = uc.getUser()) == null) throw new BoiiodException(StatusCode.NOT_LOGIN);
        String userId = user.getId().toString();
        String oldToken = this.redisTemplate.hget(ConstRedisKeyEnum.MAP_USER_ID_TOKEN.name(), userId);
        if (StringUtil.isNotEmpty(oldToken)) {// 清除旧token
            this.redisTemplate.hdel(ConstRedisKeyEnum.MAP_TOKEN_USER_ID.name(), userId);
        }
        String token = UUIDUtil.getUUID();
        this.redisTemplate.hset(ConstRedisKeyEnum.MAP_USER_ID_TOKEN.name(), userId, token);
        this.redisTemplate.hset(ConstRedisKeyEnum.MAP_TOKEN_USER_ID.name(), token, userId);
        this.request.getSession().setAttribute(ConstService.SESSION_LOGIN_USER, uc);
        this.setCookie(ConstService.COOKIE_LOGIN_TOKEN, token, ConstService.COOKIE_LOGIN_TOKEN_TIME);
    }

    protected String getCookie(String name) {
        if (StringUtil.isEmpty(name)) return null;
        Cookie[] cookies = this.request.getCookies();
        for (Cookie c : cookies) {
            String n = c.getName();
            if (name.equals(n)) return c.getValue();
        }
        return null;
    }

    /**
     * 设置cookie
     *
     * @param name
     * @param value
     * @param time  时间, 秒
     */
    protected void setCookie(String name, String value, Integer time) {
        if (StringUtil.isEmpty(name) || StringUtil.isEmpty(value)) return;
        Cookie cookie = new Cookie(name, value);
        // tomcat下多应用共享
        cookie.setPath("/");
        // 如果cookie的值中含有中文时，需要对cookie进行编码，不然会产生乱码
        try {
            URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            ExceptionUtil.getExceptionAllMsg(e);
        }
        if (time != null) cookie.setMaxAge(time);
        // 将Cookie添加到Response中,使之生效
        this.response.addCookie(cookie); // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
    }
}
