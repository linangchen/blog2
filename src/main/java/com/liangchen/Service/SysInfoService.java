package com.liangchen.Service;

import com.liangchen.POJO.SysInfoBean;

public interface SysInfoService {
    SysInfoBean getSysInfo(int id);
    int updataSysInfo(SysInfoBean sysInfoBean);
}
