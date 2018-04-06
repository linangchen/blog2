package com.liangchen.controller;

import com.liangchen.POJO.BannerBean;
import com.liangchen.Service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BannerController {
    private BannerService bannerService;
    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }
    @RequestMapping(value = "/admin/updataBanner",method = RequestMethod.POST,consumes = "application/json")
    public String addBanner(@RequestBody BannerBean bannerBean){
        int res=bannerService.updataBanner(bannerBean);
        return res!=0?"SUCCESS":"ERROR";
    }
    @RequestMapping(value = "/admin/getBanner",method = RequestMethod.GET)
    public BannerBean getBanner(int id){
        return bannerService.getBanner(id);
    }

}
