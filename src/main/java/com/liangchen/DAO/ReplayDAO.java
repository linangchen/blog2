package com.liangchen.DAO;

import com.liangchen.POJO.ReplayBean;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplayDAO {
    ReplayBean getReplayByID(int id);
    int addReplay(ReplayBean replay);
    int deleteReplayByID(int id);
}
