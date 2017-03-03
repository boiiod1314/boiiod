package com.boiiod.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by boiiod on 16/10/26.
 */
@Controller
@RequestMapping("/")
public class TestController {
    @RequestMapping("/p")
    public String jsp(String p) {
        return p;
    }
}
