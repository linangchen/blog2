package com.liangchen.controller;

import com.liangchen.POJO.GLYBean;
import com.liangchen.POJO.LoginBean;
import com.liangchen.Service.ClientService;
import com.liangchen.Service.GLYService;
import com.liangchen.myutil.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@RestController
public class GLYController {
    private GLYService glyService;
    private ClientService clientService;
    @Autowired
    public GLYController(GLYService glyService, ClientService clientService) {
        this.glyService = glyService;
        this.clientService = clientService;
    }

    @RequestMapping(value = "/admin/getGLYInfo",method = RequestMethod.GET)
    public GLYBean getGLYInfo(HttpServletRequest request){
        int id=(int)request.getSession().getAttribute("userID");
        GLYBean gly=glyService.getGLY(id);
        gly.setPassWord("**********");
        return gly;
    }
    @RequestMapping(value = "/admin/getGLYs",method = RequestMethod.GET)
    public List<GLYBean> getGLYs(HttpServletRequest request){
        //权限检查
        int id =(int)request.getSession().getAttribute("userID");
        GLYBean gly=glyService.getGLY(id);
        if (gly.getLevel()!=0){
            throw  new NullPointerException();
        }
        return glyService.getGLYList();
    }
    //获取登入记录
    @RequestMapping(value = "/admin/getLoginHistory",method = RequestMethod.GET)
    public List<LoginBean> getLoginHistory(HttpServletRequest request){
        //权限检查
        int id =(int)request.getSession().getAttribute("userID");
        GLYBean gly=glyService.getGLY(id);
        if (gly.getLevel()!=0){
            throw  new NullPointerException();
        }
        return clientService.getLoginRecord();
    }
    @RequestMapping(value = "/admin/updataGLYInfo",method = RequestMethod.POST,consumes ="application/json")
    public String UpdataGLYInfo(@RequestBody GLYBean gly){
        if (gly.getPassWord().equals("**********")){
            gly.setPassWord(null);
        }
        int res=glyService.updataGLY(gly);
        return res==0?"ERROR":"SUCCESS";
    }
    //更新管理员头像
    @RequestMapping(value = "/admin/updataGLYPic",method = RequestMethod.POST)
    public String UpdataGLYPic(@RequestPart("picture")MultipartFile picture,HttpServletRequest request){
        String fileName="";
        if(!picture.isEmpty()){
            String path=request.getServletContext().getRealPath("/WEB-INF/view/static/img/");
            fileName= FileUtil.reName(picture.getOriginalFilename());
            File file=new File(path+fileName);

//            if (!file.exists()){
//                file.mkdir();
//            }
            try {
                picture.transferTo(file);
            }catch (Exception e){
                e.printStackTrace();
                return "ERROR";
            }
        }

        return "/view/static/img/"+fileName;
    }
    @RequestMapping(value = "/admin/updataPassWord",method = RequestMethod.GET)
    public String UpdataGLYPassWord(@RequestParam String oldPassWOrd, @RequestParam String newPassword, HttpServletRequest request){
        int id=(int)request.getSession().getAttribute("userID");
        GLYBean gly=glyService.getGLY(id);
        if (gly.getPassWord().equals(oldPassWOrd)){
            gly.setPassWord(newPassword);
        }
        int res=glyService.updataGLY(gly);
        return res==0?"ERROR":"SUCCESS";
    }

}
