package com.liangchen.POJO;

public class  Notify {

   private int commentID;
   private String replayTile;
   private String replayer;
   private String replayTo;
   private String replayConte;

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getReplayTile() {
        return replayTile;
    }

    public void setReplayTile(String replayTile) {
        this.replayTile = replayTile;
    }

    public String getReplayer() {
        return replayer;
    }

    public void setReplayer(String replayer) {
        this.replayer = replayer;
    }

    public String getReplayTo() {
        return replayTo;
    }

    public void setReplayTo(String replayTo) {
        this.replayTo = replayTo;
    }

    public String getReplayConte() {
        return replayConte;
    }

    public void setReplayConte(String replayConte) {
        this.replayConte = replayConte;
    }

    public Notify(String replayTile) {
        this.replayTile = replayTile;
    }

    public Notify(int cid, String replayTile, String replayer, String replayTo, String replayConte) {
        this.commentID=cid;
        this.replayTile = replayTile;
        this.replayer = replayer;
        this.replayTo = replayTo;
        this.replayConte = replayConte;
    }
}
