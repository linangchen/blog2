package com.liangchen.Service;

import com.liangchen.POJO.ArticleBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleService {
    List<ArticleBean> getArticles(@Param("startIndex")int startIndex, @Param("nums")int nums);
    List<ArticleBean> getGlyArticles(String name);
    ArticleBean getArticleByID(int id);
    int addArticle(ArticleBean aricle);
    int deleteArticleByID(int id);
    int editArticle(ArticleBean article);
}
