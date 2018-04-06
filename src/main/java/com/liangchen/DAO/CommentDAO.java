package com.liangchen.DAO;

import com.liangchen.POJO.CommentBean;
import com.liangchen.POJO.ReplayBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDAO {
    CommentBean getCommentByID(int id);
    CommentBean getCommentByCommenter(String commenter);
    List<CommentBean> getCommentByzpID(int id);
    int insertComment(CommentBean comment);
    int deleteCommentByID(int id);
    int addReplay(CommentBean comment);
    int updataRead(int id);
}
