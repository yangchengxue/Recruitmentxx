package com.example.ycx36.recruitment.model.db;

import org.litepal.crud.LitePalSupport;


/**用户教育经历 表*/
public class usereducationexperience extends LitePalSupport {

    private String schoolName;
    private String professional; //专业
    private String highestDegree; //最高学历
    private String workTime1;
    private String workTime2;
    private String educationExperience; //学习经历
    private int isApply; //用于判断该数行数据是否已经提交（是否已经点击完成按钮）

    public int getIsApply() {
        return isApply;
    }

    public void setIsApply(int isApply) {
        this.isApply = isApply;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getHighestDegree() {
        return highestDegree;
    }

    public void setHighestDegree(String highestDegree) {
        this.highestDegree = highestDegree;
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

    public String getEducationExperience() {
        return educationExperience;
    }

    public void setEducationExperience(String educationExperience) {
        this.educationExperience = educationExperience;
    }


}
