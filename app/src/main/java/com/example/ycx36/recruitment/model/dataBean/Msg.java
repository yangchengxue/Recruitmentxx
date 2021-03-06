package com.example.ycx36.recruitment.model.dataBean;

public class Msg {
    public static final int TYPE_RECEIVED = 0;  //表示这是一条收到的消息
    public static final int TYPE_SEND = 1;    //表示这是一条发送的消息
    private String content;   //消息的内容

    public String getMsg_system() {
        return msg_system;
    }

    public void setMsg_system(String msg_system) {
        this.msg_system = msg_system;
    }

    private String msg_system;   //消息的内容
    private int type;         //消息的类型

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

    //构造方法
    public Msg(String content , String msg_system,int type){
        this.content = content;
        this.msg_system = msg_system;
        this.type = type;
    }
}
