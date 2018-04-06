package com.liangchen.myutil;

import com.liangchen.POJO.CommentBean;
import com.liangchen.POJO.ReplayBean;
import com.liangchen.Service.CommentService;
import com.liangchen.Service.ReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Commit;

import java.util.ArrayList;
import java.util.List;
@Component
@Primary
public class NewCommentUtillIpml implements CommentUtil{
    private CommentService commentService;
    private ReplayService replayService;
    @Autowired
    public NewCommentUtillIpml(CommentService commentService, ReplayService replayService) {
        this.commentService = commentService;
        this.replayService = replayService;
    }

    @Override
    public List<CommentBean> getCommentListByzpID(int id) {
        List<CommentBean> comments=commentService.getCommentByzpID(id);
        for (CommentBean c:comments){
            if (c.getReplaysId()!=null&&!c.getReplaysId().equals("")){
                c.setReplayList(replayIdList(c.getReplaysId()));
            }
        }
        return comments;
    }

    @Override
    public Integer addComment(CommentBean commentBean) {
       int  result=this.commentService.insertComment(commentBean);
        if (result!=0){
            return commentBean.getId();
        }else {
            return 0;
        }
    }

    @Override
    public String deleteCommentByID(int id) {
        //获取Comment及其replays
        CommentBean deleteComment=commentService.getCommentByID(id);
        if (deleteComment.getReplaysId()!=null&&!deleteComment.getReplaysId().equals("")){
            List<ReplayBean> replayBeanList=replayIdList(deleteComment.getReplaysId());
            if (replayBeanList.size()>0){
                for (ReplayBean r:replayBeanList){
                    replayService.deleteReplayByID(r.getId());
                }
            }
        }
        commentService.deleteCommentByID(id);

        return "SUCCESS";
    }

    @Override
    public Integer addReplay(ReplayBean replay) {
      //根据replayTo链式查找主评论，并将该回复的id插入到其replaysId下
        if (replay.getReplayTo()==0){
            return 0;
        }
        //查找主评论
        CommentBean c;
        int parentId=replay.getReplayTo();
        for (int i=0;i<8;i++){
            c=commentService.getCommentByID(parentId);
            String newReplays;
            if (c.getZpid()>1){
                this.replayService.addReplay(replay);
                if (c.getReplaysId().equals("")|c.getReplaysId()==null){
                    newReplays=replay.getId()+"";
                }else{
                    String oldReplays=c.getReplaysId();
                    newReplays=oldReplays.replace((replay.getReplayTo()+""),(replay.getReplayTo()+","+replay.getId()));
                }
                c.setReplaysId(newReplays);
                commentService.addReplay(c);
                return replay.getId();
            }else {
                parentId=replayService.getReplayByID(parentId).getReplayTo();
            }

        }

        return 0;
    }

    @Override
    public String deleteReplay(int replayId) {
        //根据replayTo链式查找主评论，并将replaysId中replayId替换掉
        ReplayBean replay=replayService.getReplayByID(replayId);
        //查找主评论
        CommentBean c;
        int parentId=replay.getReplayTo();
        for (int i=0;i<8;i++){
            c=commentService.getCommentByID(parentId);
            String newReplays;
            if (c.getZpid()>1){
                if (c.getReplaysId().equals("")|c.getReplaysId()==null){
                    newReplays=replay.getId()+"";
                }else{
                    String oldReplays=c.getReplaysId();
                    newReplays=oldReplays.replace(","+replay.getId(),"");
                }
                c.setReplaysId(newReplays);
                commentService.addReplay(c);
                replayService.deleteReplayByID(replayId);
                return "SUCCESS";
            }else {
                parentId=replayService.getReplayByID(parentId).getReplayTo();
            }

        }
        return "ERROR";
    }
    //分割字符串 拼装回复列表
     private List<ReplayBean> replayIdList(String replays){
        if (!replays.equals("")){
            String[] replaySs;
            if (replays.contains(",")){
                replaySs=replays.split(",");
            }else {
                replaySs=new String[]{replays};
            }
            List<ReplayBean> replayIs=new ArrayList<>();
            for (String s:replaySs){
                if (!s.equals("")&s!=null){
                    replayIs.add(replayService.getReplayByID(Integer.parseInt(s)));
                }
            }
            return replayIs;
        }else {
            return null;
        }
     }

}
