package com.liangchen.Service;

import com.liangchen.POJO.ArticleBean;
import com.liangchen.POJO.ProductionBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductionService {
    List<ProductionBean> getGlyProductions(String name);
    List<ProductionBean> getProductions(@Param("startIndex") int startIndex, @Param("nums") int nums);
    ProductionBean getProductionByID(int id);
    int addProduction(ProductionBean production);
    int deleteProductionByID(int id);
    int editProduction(ProductionBean production);
}
