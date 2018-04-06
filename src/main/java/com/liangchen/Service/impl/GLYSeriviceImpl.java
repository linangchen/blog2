package com.liangchen.Service.impl;

import com.liangchen.DAO.GLYDAO;
import com.liangchen.POJO.GLYBean;
import com.liangchen.Service.GLYService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GLYSeriviceImpl implements GLYService {
    private GLYDAO glydao;
    @Autowired
    public GLYSeriviceImpl(GLYDAO glydao) {
        this.glydao = glydao;
    }

    @Override
    public GLYBean getGLY(Integer id) {
        return this.glydao.getGLY(id);
    }

    @Override
    public GLYBean getGLYByName(String userName) {
        return glydao.getGLYByName(userName);
    }

    @Override
    public List<GLYBean> getGLYList() {
        return glydao.getGLYList();
    }

    @Override
    public int updataGLY(GLYBean glyBean) {
        return glydao.updataGLY(glyBean);
    }

    @Override
    public int insertGLY(GLYBean glyBean) {
        return glydao.insertGLY(glyBean);
    }

    @Override
    public int deleteGLY(Integer id) {
        return glydao.deleteGLY(id);
    }
}
