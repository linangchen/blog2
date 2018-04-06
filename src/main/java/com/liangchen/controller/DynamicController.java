package com.liangchen.controller;

import com.liangchen.POJO.*;
import com.liangchen.Service.CommentService;
import com.liangchen.Service.DynamicService;
import com.liangchen.Service.SysInfoService;
import com.liangchen.myutil.CommentUtil;
import com.liangchen.myutil.NotifyUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DynamicController {
    private DynamicService dynamicService;
    private CommentUtil commentUtil;
    private SysInfoService sysInfoService;
    private CommentService commentService;
    private NotifyUtill notifyUtill;
    @Autowired
    public DynamicController(DynamicService dynamicService, CommentUtil commentUtil, SysInfoService sysInfoService, CommentService commentService, NotifyUtill notifyUtill) {
        this.dynamicService = dynamicService;
        this.commentUtil = commentUtil;
        this.sysInfoService = sysInfoService;
        this.commentService = commentService;
        this.notifyUtill = notifyUtill;
    }

    @RequestMapping(value = "/getDynamicByID",method = RequestMethod.GET)
    public DynamicBean getDynamicByID(@RequestParam int id){
        DynamicBean dynamic=dynamicService.getDynamicByID(id);
        dynamic.setDynamicComment(commentUtil.getCommentListByzpID(id));
        return dynamic;

    }
    @RequestMapping(value = "/getDynamics",method = RequestMethod.GET)
    public List<DynamicBean> getDynamics(@RequestParam int startIndex, @RequestParam int nums){
        List<DynamicBean> dynamiList=new ArrayList<>();
        List<DynamicBean> as=dynamicService.getDynamics(startIndex,nums);
        for (DynamicBean a:as){
            if (a.isPublish()){
                dynamiList.add(a);
            }
        }
        int ii=nums;
        while (dynamiList.size()<nums){
            as=dynamicService.getDynamics(ii,ii+1);
            if(as.size()<1){
                break;
            }
            for (DynamicBean a:as){
                if (a.isPublish()){
                    dynamiList.add(a);
                }
            }
            ii++;

        }
        return dynamiList;

    }
    @RequestMapping(value = "/admin/addDynamic",method = RequestMethod.POST,consumes = "application/json")
    public String addDynamic(@RequestBody DynamicBean dynamic){
        int res=this.dynamicService.insertDynamic(dynamic);
        if (res==1){
            SysInfoBean dso=sysInfoService.getSysInfo(6666);
            int as=dso.getDynamics()+1;
            dso.setDynamics(as);
            sysInfoService.updataSysInfo(dso);
            return dynamic.getId()+"";
        }else {
            return "ERROR";
        }
    }
    @RequestMapping(value ="/admin/updataDynamicContent",method =RequestMethod.POST,consumes = "application/json")
    public String updataDynamicContent(@RequestBody DynamicBean dynamic ){
        int res=this.dynamicService.updataDynamic(dynamic);
        if (res==1){
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }
    @RequestMapping(value = "/updataDynamicLikes",method =RequestMethod.GET)
    public String updataDynamicLikes(@RequestParam int id){
        DynamicBean dynamic =this.dynamicService.getDynamicByID(id);
        int l=dynamic.getDynamicLikes();
        dynamic.setDynamicLikes(l+1);
        int res=this.dynamicService.updataDynamic(dynamic);
        if (res==1){
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }
    @RequestMapping(value = "/admin/deleteDynamic",method = RequestMethod.GET)
    public String deleteDynamic(@RequestParam int id){
        int res=this.dynamicService.deleteDynamicByID(id);
        if (res==1){
            SysInfoBean dso=sysInfoService.getSysInfo(6666);
            int as=dso.getDynamics()-1;
            dso.setDynamics(as);
            sysInfoService.updataSysInfo(dso);
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }

    @RequestMapping(value = "/admin/getDynamics",method = RequestMethod.GET)
    public List<DynamicBean> getGlyDynamics( HttpServletRequest request){
        String glyName= (String) request.getSession().getAttribute("userName");
        List<DynamicBean> dynamics=dynamicService.getGlyDynamics(glyName);
     //将评论信息加入到dynamic对象中
        for (DynamicBean d:dynamics){
            d.setDynamicComment(commentUtil.getCommentListByzpID(d.getId()));
        }
        //拼装notify
        //提取dynamic前30个字符
        String curtDynamicContent;
        String regex="/<\\/?[^>]*>/g";
        for (DynamicBean a:dynamics){
            if (a.getDynamicComment()!=null){
                for (CommentBean c:a.getDynamicComment()){
                    curtDynamicContent=a.getDynamicContent().replaceAll(regex,"").substring(0,10)+"...";
                    if (!c.isRead()){
                        notifyUtill.notifyFactory(c.getId(),curtDynamicContent,c.getCommenter(),glyName,c.getCommentContent());
                    }
                    if (c.getReplayList()!=null){
                        for (ReplayBean r:c.getReplayList()){
                            String replayToName=commentService.getCommentByID(r.getReplayTo()).getCommenter();
                            notifyUtill.notifyFactory(r.getId(),curtDynamicContent,r.getReplayer(),replayToName,r.getReplayContent());
                        }
                    }
                }
            }

        }
        
        return dynamicService.getGlyDynamics(glyName);

    }
}
