package com.boiiod.controller;

import com.boiiod.entity.User;
import com.boiiod.exception.BoiiodException;
import com.boiiod.result.ResultBuilder;
import com.boiiod.result.ResultJson;
import com.boiiod.service.UserService;
import com.boiiod.status.StatusCode;
import com.boiiod.status.UserCode;
import com.boiiod.utils.EncryptUtil;
import com.boiiod.utils.RemoteRequestUtil;
import com.boiiod.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户
 * Created by boiiod on 16/10/22.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 添加用户
     *
     * @return <br />
     * 10001 用户密码长度不符
     * 10003 用户昵称长度不符
     *
     * @throws BoiiodException
     */
    @ResponseBody
    @RequestMapping(value = "/register.json", method = RequestMethod.POST)
    public ResultJson register(User user) throws BoiiodException {
        String ip = RemoteRequestUtil.getRequestIP(this.request);
        user.setRegisterIp(ip);
        ResultJson result = null;//this.userService.register(user);
        if (result.getCode() == StatusCode.SUCCESS.getCode()) {
            this.buildUCAndSetSessionData(user);
        }
        return result;
    }

    /**
     * 手机号登录
     *
     * @param phone
     * @param password
     *
     * @return <br />
     * 10001 用户密码长度不符
     * 10004 密码错误
     * 10011 用户不存在
     *
     * @throws BoiiodException
     */
    @ResponseBody
    @RequestMapping(value = "/loginByPhone.json", method = RequestMethod.GET)
    public ResultJson loginByPhone(String phone, String password) throws BoiiodException {
        if (StringUtil.isEmpty(password))
            return ResultBuilder.buildError(UserCode.PASSWORD_LEN_NOT_ALLOW);
        User user = null;//this.userService.getByPhone(phone);
        if (user == null) return ResultBuilder.buildError(UserCode.USER_NOT_EXISTS);

        if (!EncryptUtil.sha(password).equals(user.getPassword()))
            return ResultBuilder.buildError(UserCode.PASSWORD_ERROR);

        this.buildUCAndSetSessionData(user);

        return ResultBuilder.build();
    }
}
