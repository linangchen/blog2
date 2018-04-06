package com.liangchen.myutil;

import com.liangchen.DAO.ClientDAO;
import com.liangchen.POJO.Client;
import com.liangchen.POJO.ClientBean;
import com.liangchen.Service.ClientService;
import com.liangchen.Service.impl.ClientServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientUtill {
    private ClientService clientService;
    @Autowired
    public ClientUtill(ClientService clientService) {
        this.clientService = clientService;
    }
    //将用户访问信息插入数据库

    public int clientInsert(Client client){
       return this.clientService.insertClient(client);
    }
    //获取访问记录
     public List<Client> getFWVisited(){
        return clientService.getFWVisited();
     }
}
