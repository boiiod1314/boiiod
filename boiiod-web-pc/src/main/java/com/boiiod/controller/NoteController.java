package com.boiiod.controller;

import com.boiiod.exception.BoiiodException;
import com.boiiod.service.NoteService;
import com.boiiod.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 笔记
 * Created by boiiod on 16/10/26.
 */
@Controller
@RequestMapping("/note")
public class NoteController extends BaseController {
    @Autowired
    private NoteService noteService;

    @RequestMapping(value = "/list{userId}.html")
    public ModelAndView listHtml(@PathVariable("userId") Integer userId) throws BoiiodException {
        if (StringUtil.isNotAutoId(userId)) userId = this.getUserId();
        ModelAndView mv = new ModelAndView("note/list");
        mv.addObject("userId", userId);
        return mv;
    }
}
