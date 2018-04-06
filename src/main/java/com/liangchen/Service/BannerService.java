package com.liangchen.Service;

import com.liangchen.POJO.BannerBean;

public interface BannerService {
    int updataBanner(BannerBean bannerBean);
    int deleteBanner(int id);
    //设置当前
    int setNow(int id);
    BannerBean getBanner(int id);
}
