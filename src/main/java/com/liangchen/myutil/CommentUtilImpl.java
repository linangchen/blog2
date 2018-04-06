package com.liangchen.myutil;

import com.liangchen.POJO.CommentBean;
import com.liangchen.POJO.ReplayBean;
import com.liangchen.Service.CommentService;
import com.liangchen.Service.ReplayService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommentUtilImpl implements CommentUtil {
    private CommentService commentService;
    private ReplayService replayService;
    @Autowired
    public CommentUtilImpl(CommentService commentService, ReplayService replayService) {
        this.commentService = commentService;
        this.replayService = replayService;
    }

    private List<ReplayBean> replay=new ArrayList<>();

    private int result=0;

//获取Comment对象
    public  List<CommentBean> getCommentListByzpID(int id){
        replay.clear();
        //获取主评论
        List<CommentBean> mainCommentList=commentService.getCommentByzpID(id);
        for (CommentBean c:mainCommentList){
            String replaysID=c.getReplaysId();
            if (replaysID!=null){
                if(replaysID.equals("")){
                    return mainCommentList;
                }
                if (!replaysID.contains(",")){
                    replay.add( this.replayService.getReplayByID(Integer.parseInt(replaysID)));
                }else{
                    String[] replays=replaysID.split(",");
                    for (String s:replays){
                        replay.add( this.replayService.getReplayByID(Integer.parseInt(s)));
                    }
                }

               c.setReplayList(replay);
            }
        }
        return mainCommentList;
    }
//插入评论
    public Integer addComment(CommentBean commentBean){
        result=this.commentService.insertComment(commentBean);
     if (result!=0){
         return commentBean.getId();
     }else {
         return 0;
     }
    }
//删除评论
public String deleteCommentByID(int id){
        CommentBean comment=this.commentService.getCommentByID(id);
        if (comment==null){
            return "ERROR";
        }
        //删除评论回复
        String replays=comment.getReplaysId();
        if (replays!=null &!replays.equals("")){
            String[]replaysList=replays.split(",");
            for (String r:replaysList){
                result=this.replayService.deleteReplayByID(Integer.parseInt(r));
            }
        }
    //删除主评论
    result=this.commentService.deleteCommentByID(id);
        if(result!=0){
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }
    //插入回复
    public Integer addReplay(ReplayBean replay){
        if (replay.getReplayTo()>1){
            return replay.getId();
        }
        //插入新评论
        result=this.replayService.addReplay(replay);
        //根据正则表达式提取replayTo中的回复对象 和回复对象的ID
        int replayToId=replay.getReplayTo();
//        String replayToComment=transform(replay.getReplayTo())[1];
        if (replayToId>1){
            return replay.getId();
        }
        //
        CommentBean comment=this.commentService.getCommentByID(replayToId);
        String commentReplaysId=comment.getReplaysId();
        String newReplays;
        //回复主评论
        if(comment.getZpid()!=0){
            newReplays=commentReplaysId+","+replay.getId();
            comment.setReplaysId(newReplays);
            result=this.commentService.addReplay(comment);
            if (result!=0){ return replay.getId(); }else {return 0;}
        }else {
            //二级回复
                /*
                *获得主评论的replaysId
                * 找到回复对象ID
                * 将该条回复ID插入到回复对象的后面
                * 更新主评论的replays
                * */
                int zpid=0;
                CommentBean mainComment=null;
                ReplayBean parentReplay;
                int Freplay=replayToId;
                //链式查找主评论
                while (zpid==0){
                    parentReplay=this.replayService.getReplayByID(Freplay);
                    if (parentReplay.getReplayTo()>1){
                        mainComment=this.commentService.getCommentByID(Freplay);
                        zpid=mainComment.getZpid();
                    }else{
                        Freplay=parentReplay.getReplayTo();
                    }

                }
                newReplays=mainComment.getReplaysId().replace(replayToId+"",replayToId+","+replay.getId());
                mainComment.setReplaysId(newReplays);
                result=this.commentService.addReplay(mainComment);
        }
        if(result!=0){
            return replay.getId();
        }else {
            return replay.getId();
        }
    }
//删除回复
    public String deleteReplay(int replayId){
        //获得评论
        ReplayBean replay=this.replayService.getReplayByID(replayId);
        //更新Comment对象replaysId属性
        int mainCommentID=replay.getReplayTo();
        CommentBean commentBean=this.commentService.getCommentByID(mainCommentID);
        String newReplaysId =commentBean.getReplaysId().replace(replayId+"","");
        commentBean.setReplaysId(newReplaysId);
        //删除评论
        result=this.replayService.deleteReplayByID(replayId);
        result=this.commentService.addReplay(commentBean);
        if(result!=0){
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }
    public String[] transform(String replays){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(replays);
        String replayToId=m.replaceAll("").trim();
        String replayToCommenter=replays.replace(replayToId,"").trim();
        String[] result={replayToId,replayToCommenter};
        return result ;
    }
}


