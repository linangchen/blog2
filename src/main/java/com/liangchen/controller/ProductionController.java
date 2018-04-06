package com.liangchen.controller;
import com.liangchen.POJO.*;
import com.liangchen.Service.CommentService;
import com.liangchen.Service.ProductionService;
import com.liangchen.Service.SysInfoService;
import com.liangchen.myutil.CommentUtil;
import com.liangchen.myutil.NotifyUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductionController {
    private ProductionService productionService;
    private CommentUtil commentUtil;
    private SysInfoService sysInfoService;
    private CommentService commentService;
    private NotifyUtill notifyUtill;
    @Autowired
    public ProductionController(ProductionService productionService, CommentUtil commentUtil, SysInfoService sysInfoService, CommentService commentService, NotifyUtill notifyUtill) {
        this.productionService = productionService;
        this.commentUtil = commentUtil;
        this.sysInfoService = sysInfoService;
        this.commentService = commentService;
        this.notifyUtill = notifyUtill;
    }

    @RequestMapping(value = "/getProductionByID",method = RequestMethod.GET)
    public ProductionBean getProductionByID(@RequestParam int id){
        ProductionBean production=productionService.getProductionByID(id);
        production.setProductionComment(commentUtil.getCommentListByzpID(production.getId()));
        return production;
    }
    //    @RequestMapping(value = "/getProductionCommentByID",method = RequestMethod.GET)
//    public List<CommentBean> getProductionCommentByID(@RequestParam int id){
//        return  CommentFactory.getCommentList(id);
//    }
    @RequestMapping(value = "/getProductions",method = RequestMethod.GET)
    public List<ProductionBean> getProduction(@RequestParam int startIndex, @RequestParam int nums){
        List<ProductionBean> productionList=new ArrayList<>();
        List<ProductionBean> as=productionService.getProductions(startIndex,nums);
        for (ProductionBean a:as){
            if (a.isPublish()){
                productionList.add(a);
            }
        }
        int ii=nums;
        while (productionList.size()<nums){
            as=productionService.getProductions(ii,ii+1);
            if(as.size()<1){
                break;
            }
            for (ProductionBean a:as){
                if (a.isPublish()){
                    productionList.add(a);
                }
            }
            ii++;

        }
        return productionList;

    }
    @RequestMapping(value = "/admin/addProduction",method = RequestMethod.POST,consumes = "application/json")
    public String addProduction(@RequestBody ProductionBean production){
        int res=productionService.addProduction(production);
        if (res==1){
            SysInfoBean pso=sysInfoService.getSysInfo(6666);
            int as=pso.getProductions()+1;
            pso.setProductions(as);
            sysInfoService.updataSysInfo(pso);
            return production.getId()+"";
        }else {
            return "ERROR";
        }
    }
    @RequestMapping(value ="/admin/editProductionContent",method =RequestMethod.POST,consumes = "application/json")
    public String updataProductionContent(@RequestBody ProductionBean production ){
        int res=productionService.editProduction(production);
        if (res==1){
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }
    @RequestMapping(value = "/updataProductionLikes",method =RequestMethod.GET)
    public String updataProductionLikes(@RequestParam int id){
        ProductionBean productio=productionService.getProductionByID(id);
        int l=productio.getProductionLikes();
        productio.setProductionLikes(l+1);
        int res=this.productionService.editProduction(productio);
        if (res==1){
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }
    @RequestMapping(value = "/admin/deleteProduction",method = RequestMethod.GET)
    public String deleteProduction(@RequestParam int id){
        int res=productionService.deleteProductionByID(id);
        if (res==1){
            SysInfoBean pso=sysInfoService.getSysInfo(6666);
            int as=pso.getProductions()-1;
            pso.setProductions(as);
            sysInfoService.updataSysInfo(pso);
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }
    @RequestMapping(value = "/admin/getProductions",method = RequestMethod.GET)
    public List<ProductionBean> getGlyProduction(HttpServletRequest request){
        String glyName=(String)request.getSession().getAttribute("userName");
        List<ProductionBean> productions=productionService.getGlyProductions(glyName);
        //为production对象添加评论信息
        for(ProductionBean p:productions){
            p.setProductionComment(commentUtil.getCommentListByzpID(p.getId()));
        }
        //拼装notify
        for (ProductionBean a:productions){
            if (a.getProductionComment()!=null){
                for (CommentBean c:a.getProductionComment()){
                    if (!c.isRead()){
                        notifyUtill.notifyFactory(c.getId(),a.getProductionTitle(),c.getCommenter(),glyName,c.getCommentContent());
                    }
                    if (c.getReplayList()!=null){
                        for (ReplayBean r:c.getReplayList()){
                            String replayToName=commentService.getCommentByID(r.getReplayTo()).getCommenter();
                            notifyUtill.notifyFactory(r.getId(),a.getProductionTitle(),r.getReplayer(),replayToName,r.getReplayContent());
                        }
                    }
                }
            }
        }
        return productions;
    }
}
