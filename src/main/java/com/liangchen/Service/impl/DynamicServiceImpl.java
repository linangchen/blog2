package com.liangchen.Service.impl;

import com.liangchen.DAO.DynamicDAO;
import com.liangchen.POJO.DynamicBean;
import com.liangchen.Service.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicServiceImpl implements DynamicService {
    private DynamicDAO dynamicDAO;
    @Autowired
    public DynamicServiceImpl(DynamicDAO dynamicDAO) {
        this.dynamicDAO = dynamicDAO;
    }

    @Override
    public List<DynamicBean> getGlyDynamics(String name) {
        return dynamicDAO.getGlyDynamics(name);
    }

    @Override
    public List<DynamicBean> getDynamics(int startIndex, int num) {
        return this.dynamicDAO.getDynamics(startIndex,num);
    }

    @Override
    public DynamicBean getDynamicByID(int id) {
        return this.dynamicDAO.getDynamicByID(id);
    }

    @Override
    public int updataDynamic(DynamicBean dynamic) {
        return this.dynamicDAO.updataDynamic(dynamic);
    }

    @Override
    public int deleteDynamicByID(int id) {
        return this.dynamicDAO.deleteDynamicByID(id);
    }

    @Override
    public int insertDynamic(DynamicBean dynamic) {
        return this.dynamicDAO.insertDynamic(dynamic);
    }
}
