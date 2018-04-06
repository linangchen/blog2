package com.liangchen.Service.impl;

import com.liangchen.DAO.BannerDAO;
import com.liangchen.POJO.BannerBean;
import com.liangchen.Service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannnerServiceImpl implements BannerService{
    private BannerDAO bannerDAO;
    @Autowired
    public BannnerServiceImpl(BannerDAO bannerDAO) {
        this.bannerDAO=bannerDAO;
    }

    @Override
    public int updataBanner(BannerBean bannerBean) {
        return bannerDAO.updataBanner(bannerBean);
    }

    @Override
    public int deleteBanner(int id) {
        return bannerDAO.deleteBanner(id);
    }

    @Override
    public int setNow(int id) {
        return bannerDAO.setNow(id);
    }

    @Override
    public BannerBean getBanner(int id) {
        return bannerDAO.getBanner(id);
    }
}
