package com.example.ycx36.recruitment.model.dataBean;

public class ProjectExperienceData {

    private String ProjectName;
    private String ProjectRole;  //项目中担任的角色
    private String ProjectTime1;
    private String ProjectTime2;
    private String ProjectIntroduction; //工作内容
    private String AchievementsInProject; //工作业绩
    
    public ProjectExperienceData(String ProjectName,String ProjectRole,String ProjectTime1,String ProjectTime2,String ProjectIntroduction,String AchievementsInProject){
        this.ProjectName = ProjectName;
        this.ProjectRole = ProjectRole;
        this.ProjectTime1 = ProjectTime1;
        this.ProjectTime2 = ProjectTime2;
        this.ProjectIntroduction = ProjectIntroduction;
        this.AchievementsInProject = AchievementsInProject;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        ProjectName = ProjectName;
    }

    public String getProjectRole() {
        return ProjectRole;
    }

    public void setProjectRole(String projectRole) {
        ProjectRole = projectRole;
    }

    public String getProjectTime1() {
        return ProjectTime1;
    }

    public void setProjectTime1(String projectTime1) {
        ProjectTime1 = projectTime1;
    }

    public String getProjectTime2() {
        return ProjectTime2;
    }

    public void setProjectTime2(String ProjectTime2) {
        ProjectTime2 = ProjectTime2;
    }

    public String getProjectIntroduction() {
        return ProjectIntroduction;
    }

    public void setProjectIntroduction(String projectIntroduction) {
        ProjectIntroduction = projectIntroduction;
    }

    public String getAchievementsInProject() {
        return AchievementsInProject;
    }

    public void setAchievementsInProject(String achievementsInProject) {
        AchievementsInProject = achievementsInProject;
    }

    
}
