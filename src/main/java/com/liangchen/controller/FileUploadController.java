package com.liangchen.controller;

import com.liangchen.myutil.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
public class FileUploadController {
    private HttpServletRequest request;
    @Autowired
    public FileUploadController(HttpServletRequest request) {
        this.request = request;
    }

    @RequestMapping(value = "/admin/imgUpload",method = RequestMethod.POST)
    public String Register(
            @RequestPart("picture")MultipartFile picture
    ){
        String fileName="";
        if(!picture.isEmpty()){
            String path=request.getServletContext().getRealPath("/WEB-INF/view/static/img/");
             fileName= FileUtil.reName(picture.getOriginalFilename());
            File file=new File(path+fileName);
            System.out.println(fileName);
//            if (!file.exists()){
//                file.mkdir();
//            }
            try {
                picture.transferTo(file);
            }catch (Exception e){
                return "ERROR";
            }
        }

        return "/view/static/img/"+fileName;
    }
}
