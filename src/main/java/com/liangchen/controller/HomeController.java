package com.liangchen.controller;

import com.liangchen.POJO.CommentBean;
import com.liangchen.POJO.ReplayBean;
import com.liangchen.Service.CommentService;
import com.liangchen.Service.ReplayService;
import com.liangchen.myutil.CommentUtil;
import com.liangchen.myutil.CommentUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Killer on 2017/6/23.
 */
@Controller
public class HomeController {
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String home(){
        return "index";
    }
}
