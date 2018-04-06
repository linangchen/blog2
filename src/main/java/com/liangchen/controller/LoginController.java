package com.liangchen.controller;

import com.liangchen.POJO.GLYBean;
import com.liangchen.POJO.LoginBean;
import com.liangchen.POJO.UserBean;
import com.liangchen.Service.ClientService;
import com.liangchen.Service.GLYService;
import com.liangchen.myutil.ClientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    private GLYService glyService;
    private ClientInfo clientInfo;
    private ClientService clientService;
    @Autowired
    public LoginController(GLYService glyService, ClientInfo clientInfo, ClientService clientService) {
        this.glyService = glyService;
        this.clientInfo = clientInfo;
        this.clientService = clientService;
    }

    @RequestMapping(value = "/admin/login",method = RequestMethod.GET)
    public String tologin(){
        return "redirect:/login";
    }
    @RequestMapping(value = "/admin/logout",method = RequestMethod.GET)
    public String tologout(){
        return "redirect:/logout";
    }
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();

        return "login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/admin/login",method = RequestMethod.POST)
    public @ResponseBody String login(@RequestBody UserBean user, HttpServletRequest request) {
        try {
            if(request.getSession(false)!=null){
                if(request.getSession().getAttribute("userName").equals(user.getUserName())){
                    return "SUCCESS";
                }
            }
            System.out.println(user.getUserName()+">>"+user.getPassWord());
            GLYBean gly = this.glyService.getGLYByName(user.getUserName());
            if (user.getPassWord().equals(gly.getPassWord())) {
                // 将登录记录保存至数据库
                LoginBean loginBean=new LoginBean();
                loginBean.setName(user.getUserName());
                loginBean.setIp(clientInfo.getClientIp(request));
                loginBean.setLoginTime(clientInfo.getStartDate());
                int a=clientService.insertLoginRecord(loginBean);
                //将用户信息保存至session
                request.getSession().setAttribute("userID",gly.getId());
                request.getSession().setAttribute("userName",user.getUserName());
                return "SUCCESS";
            }
        } catch (Exception e) {
            return "ERROR";
        }
        return "ERROR";
    }
}
