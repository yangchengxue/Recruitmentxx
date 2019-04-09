package com.example.ycx36.recruitment.model.db;

import org.litepal.crud.LitePalSupport;

/**用户工作经历 表*/
public class userworkexperience extends LitePalSupport {

    private String workLocation;
    private String jobTitle;
    private String workTime1;
    private String workTime2;
    private String workContent; //工作内容
    private String achievementsInWork; //工作业绩
    private int isApply; //用于判断该数行数据是否已经提交（是否已经点击完成按钮）

    public int getIsApply() {
        return isApply;
    }

    public void setIsApply(int isApply) {
        this.isApply = isApply;
    }


    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getWorkTime1() {
        return workTime1;
    }

    public void setWorkTime1(String workTime1) {
        this.workTime1 = workTime1;
    }

    public String getWorkTime2() {
        return workTime2;
    }

    public void setWorkTime2(String workTime2) {
        this.workTime2 = workTime2;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getAchievementsInWork() {
        return achievementsInWork;
    }

    public void setAchievementsInWork(String achievementsInWork) {
        this.achievementsInWork = achievementsInWork;
    }


}
