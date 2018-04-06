package com.liangchen.Service.impl;

import com.liangchen.DAO.ProductionDAO;
import com.liangchen.POJO.ProductionBean;
import com.liangchen.Service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionServiceImpl implements ProductionService {
    private ProductionDAO productionDAO;
    @Autowired
    public ProductionServiceImpl(ProductionDAO productionDAO) {
        this.productionDAO=productionDAO;
    }

    @Override
    public List<ProductionBean> getProductions(int startIndex, int nums) {
        return productionDAO.getProductions(startIndex,nums);
    }
    @Override
    public List<ProductionBean> getGlyProductions(String name){
        return productionDAO.getGlyProductions(name);
    }
    @Override
    public ProductionBean getProductionByID(int id) {
        return this.productionDAO.getProductionByID(id);
    }

    @Override
    public int addProduction(ProductionBean production) {
        return this.productionDAO.addProduction(production);
    }

    @Override
    public int deleteProductionByID(int id) {
        return this.productionDAO.deleteProductionByID(id);
    }

    @Override
    public int editProduction(ProductionBean production) {
        return this.productionDAO.editProduction(production);
    }
}
