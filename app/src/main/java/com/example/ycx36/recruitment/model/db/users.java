package com.example.ycx36.recruitment.model.db;

import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

/**用户 表*/
public class users extends LitePalSupport {


    private int id;
    private String U_Name;
    private String U_Id;
    //用户表和其他表是一对一的关系；
    private userinfo userinfo;

    private List<userworkexperience> userworkexperience = new ArrayList<>();
    private List<userprojectexperience> userprojectexperience = new ArrayList<>();
    private List<usereducationexperience> usereducationexperience = new ArrayList<>();

    public List<com.example.ycx36.recruitment.model.db.userworkexperience> getUserworkexperience() {
        return userworkexperience;
    }

    public void setUserworkexperience(List<com.example.ycx36.recruitment.model.db.userworkexperience> userworkexperience) {
        this.userworkexperience = userworkexperience;
    }

    public List<com.example.ycx36.recruitment.model.db.userprojectexperience> getUserprojectexperience() {
        return userprojectexperience;
    }

    public void setUserprojectexperience(List<com.example.ycx36.recruitment.model.db.userprojectexperience> userprojectexperience) {
        this.userprojectexperience = userprojectexperience;
    }

    public List<com.example.ycx36.recruitment.model.db.usereducationexperience> getUsereducationexperience() {
        return usereducationexperience;
    }

    public void setUsereducationexperience(List<com.example.ycx36.recruitment.model.db.usereducationexperience> usereducationexperience) {
        this.usereducationexperience = usereducationexperience;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public com.example.ycx36.recruitment.model.db.userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(com.example.ycx36.recruitment.model.db.userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public String getU_Name() {
        return U_Name;
    }

    public void setU_Name(String u_Name) {
        U_Name = u_Name;
    }

    public String getU_Id() {
        return U_Id;
    }

    public void setU_Id(String u_Id) {
        U_Id = u_Id;
    }
}
