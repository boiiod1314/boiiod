package com.boiiod.consts;

/**
 * Created by boiiod on 16/10/26.
 */
public class ConstService {
    public static final String SESSION_LOGIN_USER = "userContext";
    public static final String COOKIE_LOGIN_TOKEN = "token";
    public static final int COOKIE_LOGIN_TOKEN_TIME = 60 * 60 * 24 * 90;

    public static final int PASSWORD_LEN_MIN = 1;       // 用户密码最小长度
    public static final int PASSWORD_LEN_MAX = 32;      // 用户密码最大长度
    public static final int NICKNAME_LEN_MIN = 1;       // 用户昵称最大长度
    public static final int NICKNAME_LEN_MAX = 32;      // 用户昵称最大长度
    public static final String USERNAME_REG = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$"; // 用户名规则
}
