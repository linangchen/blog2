package com.liangchen.POJO;

import java.sql.Date;
import java.util.List;

public class DynamicBean {
    private int id;
    private String dynamicAuthor;
    private String dynamicAuthorImg;
    private String dynamicTitlePage;
    private Date dynamicCreateDate;
    private String dynamicContent;
    private int dynamicLikes;
    private boolean publish;
    private List<CommentBean> dynamicComment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDynamicAuthor() {
        return dynamicAuthor;
    }

    public void setDynamicAuthor(String dynamicAuthor) {
        this.dynamicAuthor = dynamicAuthor;
    }

    public String getDynamicAuthorImg() {
        return dynamicAuthorImg;
    }

    public void setDynamicAuthorImg(String dynamicAuthorImg) {
        this.dynamicAuthorImg = dynamicAuthorImg;
    }

    public String getDynamicTitlePage() {
        return dynamicTitlePage;
    }

    public void setDynamicTitlePage(String dynamicTitlePage) {
        this.dynamicTitlePage = dynamicTitlePage;
    }

    public Date getDynamicCreateDate() {
        return dynamicCreateDate;
    }

    public void setDynamicCreateDate(Date dynamicCreateDate) {
        this.dynamicCreateDate = dynamicCreateDate;
    }

    public String getDynamicContent() {
        return dynamicContent;
    }

    public void setDynamicContent(String dynamicContent) {
        this.dynamicContent = dynamicContent;
    }

    public int getDynamicLikes() {
        return dynamicLikes;
    }

    public void setDynamicLikes(int dynamicLikes) {
        this.dynamicLikes = dynamicLikes;
    }

    public List<CommentBean> getDynamicComment() {
        return dynamicComment;
    }

    public void setDynamicComment(List<CommentBean> dynamicComment) {
        this.dynamicComment = dynamicComment;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }
}
