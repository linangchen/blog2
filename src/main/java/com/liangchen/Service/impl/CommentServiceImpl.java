package com.liangchen.Service.impl;

import com.liangchen.DAO.CommentDAO;
import com.liangchen.POJO.CommentBean;
import com.liangchen.POJO.ReplayBean;
import com.liangchen.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentDAO commentDAO;
    @Autowired
    public CommentServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public CommentBean getCommentByID(int id) {
        return this.commentDAO.getCommentByID(id);
    }

    @Override
    public List<CommentBean> getCommentByzpID(int id) {
        return this.commentDAO.getCommentByzpID(id);
    }

    @Override
    public int insertComment(CommentBean comment) {
        return this.commentDAO.insertComment(comment);
    }

    @Override
    public int deleteCommentByID(int id) {
        return this.commentDAO.deleteCommentByID(id);
    }

    @Override
    public int addReplay(CommentBean comment) {
        return this.commentDAO.addReplay(comment);
    }

    @Override
    public CommentBean getCommentByCommenter(String commenter) {
        return this.commentDAO.getCommentByCommenter(commenter);
    }

    @Override
    public int updataRead(int id) {
        return commentDAO.updataRead(id);
    }
}
