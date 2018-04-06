package com.liangchen.Service;

import com.liangchen.POJO.Client;
import com.liangchen.POJO.LoginBean;

import java.util.List;

public interface ClientService {
     Client getClientByIp(String ip);
     int insertClient(Client client);
     Client getClientByVisittime(String time);
    int insertLoginRecord(LoginBean login);
    //获得登入记录
    List<LoginBean> getLoginRecord();
    //获得访问记录
    List<Client> getFWVisited();
}
