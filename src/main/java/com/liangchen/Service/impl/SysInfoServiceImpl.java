package com.liangchen.Service.impl;

import com.liangchen.DAO.SysInfoDAO;
import com.liangchen.POJO.SysInfoBean;
import com.liangchen.Service.SysInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysInfoServiceImpl implements SysInfoService {

    private SysInfoDAO sysInfoDAO;
    @Autowired
    public SysInfoServiceImpl(SysInfoDAO sysInfoDAO) {
        this.sysInfoDAO = sysInfoDAO;
    }

    @Override
    public SysInfoBean getSysInfo(int id) {
        return sysInfoDAO.getSysInfo(id);
    }

    @Override
    public int updataSysInfo(SysInfoBean sysInfoBean) {
        return sysInfoDAO.updataSysInfo(sysInfoBean);
    }
}
