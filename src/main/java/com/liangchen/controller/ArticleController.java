package com.liangchen.controller;

import com.liangchen.POJO.*;
import com.liangchen.Service.ArticleService;
import com.liangchen.Service.CommentService;
import com.liangchen.Service.SysInfoService;
import com.liangchen.myutil.CommentUtil;
import com.liangchen.myutil.NotifyUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
public class ArticleController {
    private ArticleService articleService;
    private CommentUtil commentUtil;
    private SysInfoService sysInfoService;
    private NotifyUtill notifyUtill;
    private CommentService commentService;
    @Autowired
    public ArticleController(ArticleService articleService, CommentUtil commentUtil, SysInfoService sysInfoService, NotifyUtill notifyUtill, CommentService commentService) {
        this.articleService = articleService;
        this.commentUtil = commentUtil;
        this.sysInfoService = sysInfoService;
        this.notifyUtill = notifyUtill;
        this.commentService = commentService;
    }

    @RequestMapping(value = "/getArticleByID",method = RequestMethod.GET)
    public ArticleBean getArticleByID(@RequestParam int id){
        ArticleBean articleBean=articleService.getArticleByID(id);
        articleBean.setArticleComment(commentUtil.getCommentListByzpID(articleBean.getId()));
        return articleBean;
    }
    //    @RequestMapping(value = "/getDynamicCommentByID",method = RequestMethod.GET)
//    public List<CommentBean> getDynamicCommentByID(@RequestParam int id){
//        return  CommentFactory.getCommentList(id);
//    }
    @RequestMapping(value = "/getArticles",method = RequestMethod.GET)
    public List<ArticleBean> getArticles(@RequestParam int startIndex, @RequestParam int nums){
        List<ArticleBean> articleList=new ArrayList<>();
        List<ArticleBean> as=articleService.getArticles(startIndex,nums);
        for (ArticleBean a:as){
            if (a.isPublish()){
                articleList.add(a);
            }

        }
        int ii=nums;
        while (articleList.size()<nums){
            as=articleService.getArticles(ii,ii+1);
            if(as.size()<1){
                break;
            }
            for (ArticleBean a:as){
                if (a.isPublish()){
                    articleList.add(a);
                }
            }
            ii++;
        }
        return articleList;
    }
    @RequestMapping(value = "/admin/addArticle",method = RequestMethod.POST,consumes = "application/json")
    public String addArticle(@RequestBody ArticleBean article){
        int res=articleService.addArticle(article);
        if (res==1){
            SysInfoBean aso=sysInfoService.getSysInfo(6666);
            int as=aso.getArticles()+1;
            aso.setArticles(as);
            sysInfoService.updataSysInfo(aso);
            return article.getId()+"";
        }else {
            return "ERROR";
        }
    }
    @RequestMapping(value ="/admin/editArticleContent",method =RequestMethod.POST,consumes = "application/json")
    public String updataArticleContent(@RequestBody ArticleBean article ){
        int res=articleService.editArticle(article);
        if (res==1){
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }
    @RequestMapping(value = "/updataArticleLikes",method =RequestMethod.GET)
    public String updataArticleLikes(@RequestParam int id){
        ArticleBean article=articleService.getArticleByID(id);
        int l=article.getArticleLikes();
        article.setArticleLikes(l+1);
        int res=this.articleService.editArticle(article);
        if (res==1){
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }
    @RequestMapping(value = "/admin/deleteArticle",method = RequestMethod.GET)
    public String deleteArticle(@RequestParam int id){
        int res=articleService.deleteArticleByID(id);
        if (res==1){
            SysInfoBean aso=sysInfoService.getSysInfo(6666);
            int as=aso.getArticles()-1;
            aso.setArticles(as);
            sysInfoService.updataSysInfo(aso);
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }
    @RequestMapping(value = "/admin/getArticles",method = RequestMethod.GET)
    public List<ArticleBean> getGlyArticles(HttpServletRequest request){
        String glyName=(String) request.getSession().getAttribute("userName");
        List<ArticleBean> articles=articleService.getGlyArticles(glyName);
        //位article对象添加评论信息
        for (ArticleBean a:articles){
            a.setArticleComment(commentUtil.getCommentListByzpID(a.getId()));
        }
        //拼装notify
        for (ArticleBean a:articles){
            if (a.getArticleComment()!=null){
                for (CommentBean c:a.getArticleComment()){
                    if (!c.isRead()){
                        notifyUtill.notifyFactory(c.getId(),a.getArticleTitle(),c.getCommenter(),glyName,c.getCommentContent());
                    }
                    if (c.getReplayList()!=null){
                        for (ReplayBean r:c.getReplayList()){
                            String replayToName=commentService.getCommentByID(r.getReplayTo()).getCommenter();
                            notifyUtill.notifyFactory(r.getId(),a.getArticleTitle(),r.getReplayer(),replayToName,r.getReplayContent());
                        }
                    }
                }
            }

        }
        return articleService.getGlyArticles(glyName);

    }

}
