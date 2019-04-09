package com.example.ycx36.recruitment.model.dataBean;

public class SetInfoDataItemBean {
    String itemName;
    String itemRightText;

    public SetInfoDataItemBean(String itemName,String itemRightText){
        this.itemName = itemName;
        this.itemRightText = itemRightText;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemRightText() {
        return itemRightText;
    }

    public void setItemRightText(String itemRightText) {
        this.itemRightText = itemRightText;
    }
}
