package com.liangchen.POJO;

import java.sql.Date;
import java.util.List;

public class ArticleBean {
    private int id;
    private String articleTitle;
    private String articleAuthor;
    private String articleAuthorImg;
    private String articleTitlePage;
    private String articleTags;
    private Date articleCreateDate;
    private String articleContent;
    private int articlePV;
    private int articleComments;
    private boolean publish;
    private List<CommentBean> articleComment;
    private int articleLikes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }
    public String getArticleAuthorImg() {
        return articleAuthorImg;
    }

    public void setArticleAuthorImg(String articleAuthorImg) {
        this.articleAuthorImg = articleAuthorImg;
    }

    public String getArticleTitlePage() {
        return articleTitlePage;
    }

    public void setArticleTitlePage(String articleTitlePage) {
        this.articleTitlePage = articleTitlePage;
    }

    public String getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(String articleTags) {
        this.articleTags = articleTags;
    }

    public Date getArticleCreateDate() {
        return articleCreateDate;
    }

    public void setArticleCreateDate(Date articleCreateDate) {
        this.articleCreateDate = articleCreateDate;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public int getArticlePV() {
        return articlePV;
    }

    public void setArticlePV(int articlePV) {
        this.articlePV = articlePV;
    }

    public int getArticleComments() {
        return articleComments;
    }

    public void setArticleComments(int articleComments) {
        this.articleComments = articleComments;
    }

    public List<CommentBean> getArticleComment() {
        return articleComment;
    }

    public void setArticleComment(List<CommentBean> articleComment) {
        this.articleComment = articleComment;
    }

    public int getArticleLikes() {
        return articleLikes;
    }

    public void setArticleLikes(int articleLikes) {
        this.articleLikes = articleLikes;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }
}
