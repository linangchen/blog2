package com.liangchen.DAO;

import com.liangchen.POJO.Client;
import com.liangchen.POJO.ClientBean;
import com.liangchen.POJO.LoginBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDAO {
     Client getClientByIp(String ip);
     int insertClient(Client client);
     int insertLoginRecord(LoginBean login);
     Client getClientByVisittime(String time);
     List<Client> getFWVisited();
     List<LoginBean> getLoginRecord();
}
