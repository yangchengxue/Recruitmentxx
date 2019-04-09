package com.example.ycx36.recruitment.model.dataBean;

import com.facebook.drawee.interfaces.DraweeController;

public class SurroundingPlaceData {

    private String name;
    private String distance;
    private String imageUri;
    private DraweeController controller;

    //构造函数1
    public SurroundingPlaceData(String name,String distance,DraweeController controller){
        this.name = name;
        this.controller = controller;
        this.distance = distance;
    }
    //构造函数2
    public SurroundingPlaceData(String name,String distance,String imageUri){
        this.name = name;
        this.imageUri = imageUri;
        this.distance = distance;
    }
    //构造函数3
    public SurroundingPlaceData(String name,DraweeController controller){
        this.name = name;
        this.controller = controller;
    }
    //构造函数4
    public SurroundingPlaceData(String name,String imageUri){
        this.name = name;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public DraweeController getController() {
        return controller;
    }

    public void setController(DraweeController controller) {
        this.controller = controller;
    }
    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

}
