package com.liangchen.POJO;

import java.sql.Date;

public class LoginBean {
   private int id;
   private String name;
   private String ip;
   private String LoginTime;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getIp() {
      return ip;
   }

   public void setIp(String ip) {
      this.ip = ip;
   }

   public String getLoginTime() {
      return LoginTime;
   }

   public void setLoginTime(String loginTime) {
      LoginTime = loginTime;
   }
}
