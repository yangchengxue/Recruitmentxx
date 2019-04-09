package com.example.ycx36.recruitment.model.dataBean;

public class PositionDataBean {

    String positionName;
    int personNumber;
    String site;
    String college;

    public PositionDataBean(String positionName,int personNumber,String site,String college){
        this.positionName = positionName;
        this.personNumber = personNumber;
        this.site = site;
        this.college = college;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

}
