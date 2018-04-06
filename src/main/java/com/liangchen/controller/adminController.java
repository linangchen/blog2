package com.liangchen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class adminController {

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String getAdmin(HttpServletRequest request){
        try{
            String glyName=request.getSession().getAttribute("userName").toString();
        }catch (Exception e){
            return "redirect:/logout";
        }
        return "admin";
    }
}
