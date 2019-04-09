package com.example.ycx36.recruitment.model.db;


import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;

/**用户信息 表*/
public class userinfo extends LitePalSupport {

    private String userName;
    private String userBirthday;
    private String userNation;
    private String userSex;
    private String userTelephone;
    private String userZhiCheng;   //职称
    private String userPlaceOfBirth;//籍贯
    private String userHighestEducation; //最高学历
    private String userHighestOffering;//最高学位
    private String userPoliticsStatus;//政治面貌
    private String userJobCandidates; //应聘岗位
    private String userMarriageStatus;//婚姻状况


    public String getUserJobCandidates() {
        return userJobCandidates;
    }

    public void setUserJobCandidates(String userJobCandidates) {
        this.userJobCandidates = userJobCandidates;
    }



    public String getUserNation() {
        return userNation;
    }

    public void setUserNation(String userNation) {
        this.userNation = userNation;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getUserZhiCheng() {
        return userZhiCheng;
    }

    public void setUserZhiCheng(String userZhiCheng) {
        this.userZhiCheng = userZhiCheng;
    }

    public String getUserPlaceOfBirth() {
        return userPlaceOfBirth;
    }

    public void setUserPlaceOfBirth(String userPlaceOfBirth) {
        this.userPlaceOfBirth = userPlaceOfBirth;
    }

    public String getUserHighestEducation() {
        return userHighestEducation;
    }

    public void setUserHighestEducation(String userHighestEducation) {
        this.userHighestEducation = userHighestEducation;
    }

    public String getUserHighestOffering() {
        return userHighestOffering;
    }

    public void setUserHighestOffering(String userHighestOffering) {
        this.userHighestOffering = userHighestOffering;
    }

    public String getUserPoliticsStatus() {
        return userPoliticsStatus;
    }

    public void setUserPoliticsStatus(String userPoliticsStatus) {
        this.userPoliticsStatus = userPoliticsStatus;
    }

    public String getUserMarriageStatus() {
        return userMarriageStatus;
    }

    public void setUserMarriageStatus(String userMarriageStatus) {
        this.userMarriageStatus = userMarriageStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }
}
