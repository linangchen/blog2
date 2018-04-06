package com.liangchen.controller;

import com.liangchen.POJO.Notify;
import com.liangchen.POJO.NotifyList;
import com.liangchen.myutil.NotifyUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotifyController {

    private NotifyUtill notifyUtill;
    private NotifyList notifyList;
    @Autowired
    public NotifyController(NotifyUtill notifyUtill, NotifyList notifyList) {
        this.notifyUtill = notifyUtill;
        this.notifyList = notifyList;
    }

    @RequestMapping(value = "/admin/getNotifys",method = RequestMethod.GET)
    public List<Notify> getNotifys(){
        return notifyList.getNotifies();
    }
    @RequestMapping(value = "/admin/ignoreNotify",method = RequestMethod.GET)
    public String ignoreNotify(@RequestParam int cid){
        return notifyUtill.ignore(cid)!=0?"SUCCESS":"ERROR";
    }
    @RequestMapping(value = "/admin/deleteNotify",method = RequestMethod.GET)
    public String delete(@RequestParam int cid){
        return notifyUtill.delete(cid)!=0?"SUCCESS":"ERROR";
    }
    @RequestMapping(value = "/admin/clearAll",method = RequestMethod.GET)
    public String clearAll(){
        notifyUtill.clearAll();
        return "SUCCESS";
    }
}
