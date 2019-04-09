package com.example.ycx36.recruitment.model.db;

import org.litepal.crud.LitePalSupport;

/**用户项目经历 表*/
public class userprojectexperience extends LitePalSupport {
    private String projectName;
    private String projectRole;  //项目中担任的角色
    private String projectTime1;
    private String projectTime2;
    private String projectIntroduction; //项目简介
    private String achievementsInProject; //项目业绩
    private int isApply; //用于判断该数行数据是否已经提交（是否已经点击完成按钮）

    public int getIsApply() {
        return isApply;
    }

    public void setIsApply(int isApply) {
        this.isApply = isApply;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }

    public String getProjectTime1() {
        return projectTime1;
    }

    public void setProjectTime1(String projectTime1) {
        this.projectTime1 = projectTime1;
    }

    public String getProjectTime2() {
        return projectTime2;
    }

    public void setProjectTime2(String projectTime2) {
        this.projectTime2 = projectTime2;
    }

    public String getProjectIntroduction() {
        return projectIntroduction;
    }

    public void setProjectIntroduction(String projectIntroduction) {
        this.projectIntroduction = projectIntroduction;
    }

    public String getAchievementsInProject() {
        return achievementsInProject;
    }

    public void setAchievementsInProject(String achievementsInProject) {
        this.achievementsInProject = achievementsInProject;
    }


}
