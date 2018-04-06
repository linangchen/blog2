package com.liangchen.DAO;

import com.liangchen.POJO.DynamicBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DynamicDAO {
    //获取从starIndex位置开始的num个对象
    List<DynamicBean> getDynamics(@Param("startIndex") int startIndex, @Param("num") int num);
    List<DynamicBean> getGlyDynamics(String name);
    DynamicBean getDynamicByID(int id);
    int updataDynamic(DynamicBean dynamic);
    int deleteDynamicByID(int id);
    int insertDynamic(DynamicBean dynamic);
}
