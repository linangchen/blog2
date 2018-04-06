package com.liangchen.Service.impl;

import com.liangchen.DAO.ClientDAO;
import com.liangchen.POJO.Client;
import com.liangchen.POJO.LoginBean;
import com.liangchen.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientDAO clientDAO;
    @Autowired
    public ClientServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public Client getClientByIp(String ip) {
        return this.clientDAO.getClientByIp(ip);
    }

    @Override
    public int insertClient(Client client) {
        return this.clientDAO.insertClient(client);
    }

    @Override
    public Client getClientByVisittime(String time) {
        return this.clientDAO.getClientByVisittime(time);
    }

    @Override
    public int insertLoginRecord(LoginBean login) {
        return clientDAO.insertLoginRecord(login);
    }

    @Override
    public List<LoginBean> getLoginRecord() {
        return clientDAO.getLoginRecord();
    }

    @Override
    public List<Client> getFWVisited() {
        return clientDAO.getFWVisited();
    }
}
