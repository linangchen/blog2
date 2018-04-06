package com.liangchen.Service.impl;

import com.liangchen.DAO.ArticleDAO;
import com.liangchen.POJO.ArticleBean;
import com.liangchen.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleDAO articleDAO;
    @Autowired
    public ArticleServiceImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }
    @Override
    public List<ArticleBean> getArticles(int startIndex, int nums) {
        return this.articleDAO.getArticles(startIndex,nums);
    }

    @Override
    public List<ArticleBean> getGlyArticles(String name) {
        return articleDAO.getGlyArticles(name);
    }

    @Override
    public ArticleBean getArticleByID(int id) {
        return this.articleDAO.getArticleByID(id);
    }

    @Override
    public int addArticle(ArticleBean aricle) {
        return this.articleDAO.addArticle(aricle);
    }

    @Override
    public int deleteArticleByID(int id) {
        return this.articleDAO.deleteArticleByID(id);
    }

    @Override
    public int editArticle(ArticleBean article) {
        return this.articleDAO.editArticle(article);
    }
}
