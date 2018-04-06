package com.liangchen.Service;

import com.liangchen.POJO.GLYBean;

import java.util.List;

public interface GLYService {
    GLYBean getGLY(Integer id);
    GLYBean getGLYByName(String userName);
    List<GLYBean> getGLYList();
    int updataGLY(GLYBean glyBean);
    int insertGLY(GLYBean glyBean);
    int deleteGLY(Integer id);
}
