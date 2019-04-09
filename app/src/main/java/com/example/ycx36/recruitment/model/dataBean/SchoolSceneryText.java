package com.example.ycx36.recruitment.model.dataBean;

import android.net.Uri;

import com.facebook.drawee.interfaces.DraweeController;


/**学校风景浏览 数据类*/
public class SchoolSceneryText {

    private String name;
    private String imageUri;
    private DraweeController controller;
    
    //构造函数1
    public SchoolSceneryText(String name,DraweeController controller){
        this.name = name;
        this.controller = controller;
    }
    //构造函数2
    public SchoolSceneryText(String name,String imageUri){
        this.name = name;
        this.imageUri = imageUri;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getimageUri() {
        return imageUri;
    }

    public void setimageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public DraweeController getController() {
        return controller;
    }

    public void setController(DraweeController controller) {
        this.controller = controller;
    }

}
