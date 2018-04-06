package com.liangchen.myutil;

import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    //对上传的文件进行限制
    public static Boolean checkFileSize(MultipartFile file){
        return file.getSize()<=2097152;
    }
    //对文件进行重命名
    public static String reName(String fileName){
        String suffix=fileName.substring(fileName.indexOf("."),fileName.length());
        Date currentTime=new java.util.Date(System.currentTimeMillis());
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
        String prefix=format.format(currentTime);
        return prefix+(int)Math.floor(Math.random()*10)+suffix;
    }

}
