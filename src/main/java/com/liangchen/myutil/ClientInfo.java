package com.liangchen.myutil;

import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Commit;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class ClientInfo {
    HttpServletRequest httpServletRequest;
    //获取用户Ip
    public String getClientIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    //获取用户设备信息
    public String getClienDevice(HttpServletRequest request){
        String deviceInfo=request.getHeader("User-Agent");
        String device;
        if (deviceInfo.contains("Android")){
            device="Android";
        }else if(deviceInfo.contains("iPhone")){
            device="iphone";
        }else if(deviceInfo.contains("Windows")){
            device="window";
        }else if(deviceInfo.contains("iPad")){
            device="ipad";
        }else if(deviceInfo.contains("Mac")){
            device="mac";
        }else if(deviceInfo.contains("Linux")){
            device="linux";
        }
        else {
            device="未知设备";
        }
        return device;
    }
    public String getStartDate(){
        Date currentTime=new java.util.Date(System.currentTimeMillis());
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(currentTime);
    }
}
