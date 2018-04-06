package com.liangchen.Service;

import com.liangchen.POJO.CommentBean;
import com.liangchen.POJO.ReplayBean;

import java.util.List;

public interface CommentService {
    CommentBean getCommentByID(int id);
    CommentBean getCommentByCommenter(String commenter);
    List<CommentBean> getCommentByzpID(int id);
    int insertComment(CommentBean comment);
    int deleteCommentByID(int id);
    int addReplay(CommentBean comment);
    int updataRead(int id);

}
