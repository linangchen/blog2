package com.liangchen.POJO;

public class ReplayBean {
    private int id;
    private String replayer;
    private String replayerImg;
    private int replayTo;
    private String replayContent;
    private String replayDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReplayer() {
        return replayer;
    }

    public int getReplayTo() {
        return replayTo;
    }

    public void setReplayTo(int replayTo) {
        this.replayTo = replayTo;
    }

    public void setReplayer(String replayer) {
        this.replayer = replayer;
    }
    public String getReplayContent() {
        return replayContent;
    }

    public void setReplayContent(String replayContent) {
        this.replayContent = replayContent;
    }

    public String getReplayDate() {
        return replayDate;
    }

    public void setReplayDate(String replayDate) {
        this.replayDate = replayDate;
    }

    public String getReplayerImg() {
        return replayerImg;
    }

    public void setReplayerImg(String replayerImg) {
        this.replayerImg = replayerImg;
    }
}
