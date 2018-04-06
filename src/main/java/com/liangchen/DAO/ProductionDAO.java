package com.liangchen.DAO;


import com.liangchen.POJO.ProductionBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionDAO {
    List<ProductionBean> getProductions(@Param("startIndex") int startIndex, @Param("nums") int nums);
    List<ProductionBean> getGlyProductions(String name);
    ProductionBean getProductionByID(int id);
    int addProduction(ProductionBean production);
    int deleteProductionByID(int id);
    int editProduction(ProductionBean production);
}
