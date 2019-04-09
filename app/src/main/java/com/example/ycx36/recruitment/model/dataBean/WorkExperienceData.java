package com.example.ycx36.recruitment.model.dataBean;

public class WorkExperienceData {

    private String WorkLocation;
    private String JobTitle;
    private String WorkTime1;
    private String WorkTime2;
    private String WorkContent; //工作内容
    private String AchievementsInWork; //工作业绩


    public WorkExperienceData(String WorkLocation,String JobTitle,String WorkTime1,String WorkTime2,String WorkContent,String AchievementsInWork){
        this.WorkLocation = WorkLocation;
        this.JobTitle = JobTitle;
        this.WorkTime1 = WorkTime1;
        this.WorkTime2 = WorkTime2;
        this.WorkContent = WorkContent;
        this.AchievementsInWork = AchievementsInWork;
    }

    public String getWorkLocation() {
        return WorkLocation;
    }

    public void setWorkLocation(String workLocation) {
        WorkLocation = workLocation;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getWorkTime1() {
        return WorkTime1;
    }

    public void setWorkTime1(String workTime1) {
        WorkTime1 = workTime1;
    }

    public String getWorkTime2() {
        return WorkTime2;
    }

    public void setWorkTime2(String workTime2) {
        WorkTime2 = workTime2;
    }

    public String getWorkContent() {
        return WorkContent;
    }

    public void setWorkContent(String workContent) {
        WorkContent = workContent;
    }

    public String getAchievementsInWork() {
        return AchievementsInWork;
    }

    public void setAchievementsInWork(String achievementsInWork) {
        AchievementsInWork = achievementsInWork;
    }


}
