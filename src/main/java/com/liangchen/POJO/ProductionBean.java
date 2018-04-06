package com.liangchen.POJO;

import java.sql.Date;
import java.util.List;

public class ProductionBean {
    private int id;
    private String productionTitle;
    private String productionAuthor;
    private String productionAuthorImg;
    private Date productionCreateDate;
    private String productionTitlePage;
    private String productionDescribes;
    private String productionContent;
    private int productionPV;
    private int productionComments;
    private boolean publish;
    private List<CommentBean> productionComment;
    private int productionLikes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductionTitle() {
        return productionTitle;
    }

    public void setProductionTitle(String productionTitle) {
        this.productionTitle = productionTitle;
    }

    public String getProductionAuthor() {
        return productionAuthor;
    }

    public void setProductionAuthor(String productionAuthor) {
        this.productionAuthor = productionAuthor;
    }

    public String getProductionAuthorImg() {
        return productionAuthorImg;
    }

    public void setProductionAuthorImg(String productionAuthorImg) {
        this.productionAuthorImg = productionAuthorImg;
    }

    public Date getProductionCreateDate() {
        return productionCreateDate;
    }

    public void setProductionCreateDate(Date productionCreateDate) {
        this.productionCreateDate = productionCreateDate;
    }

    public String getProductionTitlePage() {
        return productionTitlePage;
    }

    public void setProductionTitlePage(String productionTitlePage) {
        this.productionTitlePage = productionTitlePage;
    }

    public String getProductionDescribes() {
        return productionDescribes;
    }

    public void setProductionDescribes(String productionDescribes) {
        this.productionDescribes = productionDescribes;
    }

    public String getProductionContent() {
        return productionContent;
    }

    public void setProductionContent(String productionContent) {
        this.productionContent = productionContent;
    }

    public int getProductionPV() {
        return productionPV;
    }

    public void setProductionPV(int productionPV) {
        this.productionPV = productionPV;
    }

    public int getProductionComments() {
        return productionComments;
    }

    public void setProductionComments(int productionComments) {
        this.productionComments = productionComments;
    }

    public List<CommentBean> getProductionComment() {
        return productionComment;
    }

    public void setProductionComment(List<CommentBean> productionComment) {
        this.productionComment = productionComment;
    }

    public int getProductionLikes() {
        return productionLikes;
    }

    public void setProductionLikes(int productionLikes) {
        this.productionLikes = productionLikes;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }
}
