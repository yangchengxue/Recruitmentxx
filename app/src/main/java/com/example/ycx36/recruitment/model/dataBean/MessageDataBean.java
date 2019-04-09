package com.example.ycx36.recruitment.model.dataBean;

import com.facebook.drawee.interfaces.DraweeController;

public class MessageDataBean {

    private String UserName;
    private String Message;
    private String ImageUri;
    private String MessageDate;
    private DraweeController controller;


    //构造函数1
    public MessageDataBean(String UserName,String Message,String MessageDate,DraweeController controller){
        this.UserName = UserName;
        this.Message = Message;
        this.MessageDate = MessageDate;
        this.controller = controller;
    }
    //构造函数2
    public MessageDataBean(String UserName,String Message,String MessageDate,String ImageUri){
        this.UserName = UserName;
        this.Message = Message;
        this.MessageDate = MessageDate;
        this.ImageUri = ImageUri;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String meessage) {
        Message = meessage;
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(String imageUri) {
        ImageUri = imageUri;
    }

    public String getMessageDate() {
        return MessageDate;
    }

    public void setMessageDate(String messageDate) {
        MessageDate = messageDate;
    }

    public DraweeController getController() {
        return controller;
    }

    public void setController(DraweeController controller) {
        this.controller = controller;
    }




}
