package com.liangchen.DAO;

import com.liangchen.POJO.ArticleBean;
import com.liangchen.POJO.DynamicBean;
import com.liangchen.POJO.ProductionBean;
import com.liangchen.POJO.SysInfoBean;
import org.springframework.stereotype.Repository;

@Repository
public interface SysInfoDAO {
   SysInfoBean getSysInfo(int id);
   int updataSysInfo(SysInfoBean sysInfoBean);
}
