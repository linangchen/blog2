package com.liangchen.Service.impl;

import com.liangchen.DAO.ReplayDAO;
import com.liangchen.POJO.ReplayBean;
import com.liangchen.Service.ReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplayServiceImpl implements ReplayService {
    private ReplayDAO replayDAO;
    @Autowired
    public ReplayServiceImpl(ReplayDAO replayDAO){
     this.replayDAO=replayDAO;
    }
    @Override
    public ReplayBean getReplayByID(int id) {
        return this.replayDAO.getReplayByID(id);
    }

    @Override
    public int addReplay(ReplayBean replay) {
        return this.replayDAO.addReplay(replay);
    }

    @Override
    public int deleteReplayByID(int id) {
        return this.replayDAO.deleteReplayByID(id);
    }
}
