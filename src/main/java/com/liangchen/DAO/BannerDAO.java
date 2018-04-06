package com.liangchen.DAO;

import com.liangchen.POJO.BannerBean;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerDAO {
    int updataBanner(BannerBean bannerBean);
    int deleteBanner(int id);
    //设置当前
    int setNow(int id);
    BannerBean getBanner(int id);
}
