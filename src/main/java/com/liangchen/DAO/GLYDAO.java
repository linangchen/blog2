package com.liangchen.DAO;

import com.liangchen.POJO.GLYBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GLYDAO {
    GLYBean getGLY(Integer id);
    List<GLYBean> getGLYList();
    GLYBean getGLYByName(String userName);
    int  updataGLY(GLYBean glyBean);
    int insertGLY(GLYBean glyBean);
    int deleteGLY(Integer id);
}
