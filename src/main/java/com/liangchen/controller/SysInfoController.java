package com.liangchen.controller;

import com.liangchen.POJO.Client;
import com.liangchen.POJO.SysInfoBean;
import com.liangchen.Service.SysInfoService;
import com.liangchen.myutil.ClientUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SysInfoController {
    private ClientUtill clientUtill;
    private SysInfoService sysInfoService;
    @Autowired
    public SysInfoController(ClientUtill clientUtill, SysInfoService sysInfoService) {
        this.clientUtill = clientUtill;
        this.sysInfoService = sysInfoService;
    }

    @RequestMapping(value = "/admin/getSysInfo",method = RequestMethod.GET)
    public SysInfoBean getSysInfo(){
        return sysInfoService.getSysInfo(6666);
    }

    @RequestMapping(value = "/admin/getFWVisited",method = RequestMethod.GET)
    public List<Client> getFWVisited(){
        return clientUtill.getFWVisited();
    }

}
