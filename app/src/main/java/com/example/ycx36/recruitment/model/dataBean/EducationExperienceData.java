package com.example.ycx36.recruitment.model.dataBean;

public class EducationExperienceData {

    private String SchoolName;
    private String Professional; //专业
    private String HighestDegree; //最高学历
    private String WorkTime1;
    private String WorkTime2;
    private String EducationExperience; //学习经历

    public EducationExperienceData(String SchoolName,String Professional,String HighestDegree,String WorkTime1,String WorkTime2,String EducationExperience){
        this.SchoolName = SchoolName;
        this.Professional = Professional;
        this.HighestDegree = HighestDegree;
        this.WorkTime1 = WorkTime1;
        this.WorkTime2 = WorkTime2;
        this.EducationExperience = EducationExperience;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String schoolName) {
        SchoolName = schoolName;
    }

    public String getProfessional() {
        return Professional;
    }

    public void setProfessional(String professional) {
        Professional = professional;
    }

    public String getHighestDegree() {
        return HighestDegree;
    }

    public void setHighestDegree(String highestDegree) {
        HighestDegree = highestDegree;
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

    public String getEducationExperience() {
        return EducationExperience;
    }

    public void setEducationExperience(String educationExperience) {
        EducationExperience = educationExperience;
    }


}
