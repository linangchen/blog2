package com.liangchen.Service;

import com.liangchen.POJO.ReplayBean;

public interface ReplayService {
    ReplayBean getReplayByID(int id);
    int addReplay(ReplayBean replay);
    int deleteReplayByID(int id);
}
