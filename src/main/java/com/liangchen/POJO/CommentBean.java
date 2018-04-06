package com.liangchen.POJO;
import java.sql.Date;
import java.util.List;

public class CommentBean {
    private int id;
    private int zpid;
    private String replaysId;
    private String replayerImg;
    private String commenter;
    private String commentContent;
    private Date commentDate;
    private boolean isRead;
    private List<ReplayBean> replayList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZpid() {
        return zpid;
    }

    public void setZpid(int zpid) {
        this.zpid = zpid;
    }

    public String getReplaysId() {
        return replaysId;
    }

    public void setReplaysId(String replaysId) {
        this.replaysId = replaysId;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public List<ReplayBean> getReplayList() {
        return replayList;
    }

    public void setReplayList(List<ReplayBean> replayList) {
        this.replayList = replayList;
    }

    public String getReplayerImg() {
        return replayerImg;
    }

    public void setReplayerImg(String replayerImg) {
        this.replayerImg = replayerImg;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
