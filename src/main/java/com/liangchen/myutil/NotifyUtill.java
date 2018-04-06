package com.liangchen.myutil;

import com.liangchen.POJO.Notify;
import com.liangchen.POJO.NotifyList;
import com.liangchen.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class NotifyUtill {
    private CommentService commentService;
    private CommentUtil commentUtil;
    private NotifyList notifyList;
    @Autowired
    public NotifyUtill(CommentService commentService, CommentUtil commentUtil, NotifyList notifyList) {
        this.commentService = commentService;
        this.commentUtil = commentUtil;
        this.notifyList = notifyList;
    }

    private List<Notify> notifies=new CopyOnWriteArrayList<>();
    //生成Notify对象
    public void notifyFactory(int cid, String replayTile, String replayer, String replayTo, String replayConte){
        Notify notify=new Notify(cid, replayTile,replayer,replayTo,replayConte);
        notifies.add(notify);
        notifyList.setNotifies(notifies);
    }
    public int ignore(int cid){
        for (Notify n:notifies){
            if (n.getCommentID()==cid){
                notifies.remove(n);
                notifyList.setNotifies(notifies);
            }
        }
        return commentService.updataRead(cid);
    }
    public int delete(int cid){
        //删除逻辑
        //忽略通知
        int res1=ignore(cid);
        //删除评论
        String res2=commentUtil.deleteCommentByID(cid);

        return res1;

    }
    public void clearAll(){
        notifies=new CopyOnWriteArrayList<>();
    }
}
