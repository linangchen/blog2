package com.liangchen.Service;

import com.liangchen.POJO.DynamicBean;

import java.util.List;

public interface DynamicService {
    List<DynamicBean> getDynamics(int startIndex, int num);
    List<DynamicBean> getGlyDynamics(String name);
    DynamicBean getDynamicByID(int id);
    int updataDynamic(DynamicBean dynamic);
    int deleteDynamicByID(int id);
    int insertDynamic(DynamicBean dynamic);
}
