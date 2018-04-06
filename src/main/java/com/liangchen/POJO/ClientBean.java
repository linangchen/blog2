package com.liangchen.POJO;

import java.sql.Date;

public class ClientBean {
    private int id;
    private String ip;
    private String device;
    private long time;
    private String visitDate;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }



    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ClientBean(String ip, String device, long time, String visitDate) {
        this.ip = ip;
        this.device = device;
        this.time = time;

        this.visitDate = visitDate;
    }
    public ClientBean(){}
}
