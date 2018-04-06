package com.liangchen.myutil;

import com.liangchen.POJO.CommentBean;
import com.liangchen.POJO.ReplayBean;

import java.util.List;

public interface CommentUtil {
    List<CommentBean> getCommentListByzpID(int id);
    Integer  addComment(CommentBean commentBean);
    String deleteCommentByID(int id);
    Integer addReplay(ReplayBean replay);
    String deleteReplay(int replayId);

}
