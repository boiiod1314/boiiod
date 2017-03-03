package com.boiiod.controller;

import com.boiiod.exception.BoiiodException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by boiiod on 16/10/26.
 */
@Controller
@RequestMapping("/")
public class RootController extends BaseController {

    @RequestMapping("/")
    public String index() throws BoiiodException {
        Integer userId;
        if ((userId = this.getUserId()) != null) return "redirect:/note/list" + userId + ".html";
        return "index/index";
    }

    @RequestMapping("/logout.login")
    public String logout() throws BoiiodException {
        this.clearCookieAndSessionInfo();
        return "redirect:/";
    }
}
