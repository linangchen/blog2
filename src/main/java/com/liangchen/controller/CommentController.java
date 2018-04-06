package com.liangchen.controller;

import com.liangchen.POJO.CommentBean;
import com.liangchen.POJO.ReplayBean;
import com.liangchen.Service.CommentService;
import com.liangchen.myutil.CommentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    private CommentUtil commentUtil;
    private CommentService commentService;
    @Autowired
    public CommentController(CommentUtil commentUtil, CommentService commentService) {
        this.commentUtil = commentUtil;
        this.commentService = commentService;
    }

    @RequestMapping(value = "/addComment",method = RequestMethod.POST,consumes = "application/json")
    public  Integer addComment(@RequestBody CommentBean comment){
        return commentUtil.addComment(comment);
    }

    @RequestMapping(value = "/addReplay",method = RequestMethod.POST,consumes = "application/json" )
    public Integer addReplay(@RequestBody ReplayBean replay){

        return commentUtil.addReplay(replay);
    }
//    @RequestMapping(value = "/admin/deleteComment",method = RequestMethod.GET)
//    public Integer deleteComment(@RequestParam int id){
//        //判断是Comment还是Replay
//        commentService.getCommentByID(id);
//    }
}
