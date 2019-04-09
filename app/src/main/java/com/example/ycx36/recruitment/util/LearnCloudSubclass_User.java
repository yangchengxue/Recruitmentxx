package com.example.ycx36.recruitment.util;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;


@AVClassName("LearnCloudSubclass_User")
public class LearnCloudSubclass_User extends AVUser {

    //构造函数
    public LearnCloudSubclass_User(){}

    public String getuserName() {
        return getString("username");
    }
    public void setuserName(String value) {
        put("username", value);
    }

    public String getuserPhotoUri() {
        return getString("userPhotoUri");
    }
    public void setuserPhotoUri(String value) {
        put("userPhotoUri", value);
    }

    public String getuserSex() {
        return getString("userSex");
    }
    public void setuserSex(String value) {
        put("userSex", value);
    }

    public String getuserProvince() {
        return getString("userProvince");
    }
    public void setuserProvince(String value) {
        put("userProvince", value);
    }

    public String getuserCity() {
        return getString("userCity");
    }
    public void setuserCity(String value) {
        put("userCity", value);
    }


}
