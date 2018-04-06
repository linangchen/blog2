package com.liangchen.config;

import com.liangchen.POJO.Client;
import com.liangchen.POJO.ClientBean;
import com.liangchen.POJO.SysInfoBean;
import com.liangchen.Service.SysInfoService;
import com.liangchen.myutil.ClientInfo;
import com.liangchen.myutil.ClientUtill;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
public class RootFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) request;
        addclient(httpServletRequest);
        chain.doFilter(request,response);
    }


    @Override
    public void destroy() {

    }
    private ClientUtill clientUtill;
    private SysInfoService sysInfoService;
    @Autowired
    public RootFilter(ClientUtill clientUtill, SysInfoService sysInfoService) {
        this.clientUtill = clientUtill;
        this.sysInfoService = sysInfoService;
    }

    private Set<ClientBean> clientBeanList=new CopyOnWriteArraySet<>();
    //获取请求信息IP 设备 访问时间等
    private void addclient(HttpServletRequest request){
        ClientInfo clientInfo=new ClientInfo();
        String device=clientInfo.getClienDevice(request);
        String ip=clientInfo.getClientIp(request);
        String visitDate=clientInfo.getStartDate();
        long time=new Date().getTime();
        ClientBean client=new ClientBean(ip,device,time,visitDate);
        Client client1=new Client(ip,device,visitDate);
        int i=0;
        if (clientBeanList.size()==0){
            clientBeanList.add(client);
            clientUtill.clientInsert(client1);
        }else {
            for (ClientBean c:clientBeanList){
                if (ip.equals(c.getIp())){
//                   //更新用户访问时间
                    c.setTime(time);
                    break;

                }
                //过滤重复的用户
                if (!ip.equals(c.getIp())){
                    i++;
                    if(i==clientBeanList.size()&&i>2){
                        clientBeanList.add(client);
                       SysInfoBean rfo=sysInfoService.getSysInfo(6666);
                       int pvs=rfo.getPageViews()+1;
                       rfo.setPageViews(pvs);
                       sysInfoService.updataSysInfo(rfo);
                       clientUtill.clientInsert(client1);
                    }
                }

            }
        }
        request.getServletContext().setAttribute("onlineClient",clientBeanList);
        tjUser(request);
    }
    //删除长时间不活动的用户
    private void tjUser(HttpServletRequest request){
        Set<ClientBean> clientList=(CopyOnWriteArraySet<ClientBean>)request.getServletContext().getAttribute("onlineClient");
        long cureeTime=new Date().getTime();
        long t;
        for (ClientBean c: clientList){
            t= cureeTime-c.getTime();
            if(t>600000){
                clientList.remove(c);
                request.getServletContext().setAttribute("onlineClient",clientBeanList);
            }
        }
    }

}
