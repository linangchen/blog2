package com.liangchen.DAO;

import com.liangchen.POJO.ArticleBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticleDAO {
    List<ArticleBean> getArticles(@Param("startIndex")int startIndex,@Param("nums")int nums);
    List<ArticleBean> getGlyArticles(String name);
    ArticleBean getArticleByID(int id);
    int addArticle(ArticleBean article);
    int deleteArticleByID(int id);
    int editArticle(ArticleBean article);
}
