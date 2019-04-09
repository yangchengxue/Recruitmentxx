package com.example.ycx36.recruitment.util;


import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("learnCloudtest")
public class learnCloudtest extends AVObject {

    //构造函数
    public learnCloudtest(){}

    public String getName() {
        return getString("name");
    }
    public void setName(String value) {
        put("name", value);
    }
}
