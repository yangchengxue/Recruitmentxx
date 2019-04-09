package com.example.ycx36.recruitment.model.dataBean;

public class WorkExperience_Simple_Data {

    private String WorkName;
    private String Time1;
    private String Time2;

    public WorkExperience_Simple_Data(String WorkName,String Time1,String Time2){
        this.WorkName = WorkName;
        this.Time1 = Time1;
        this.Time2 = Time2;
    }
    public String getWorkName() {
        return WorkName;
    }

    public void setWorkName(String workName) {
        WorkName = workName;
    }

    public String getTime1() {
        return Time1;
    }

    public void setTime1(String time1) {
        Time1 = time1;
    }

    public String getTime2() {
        return Time2;
    }

    public void setTime2(String time2) {
        Time2 = time2;
    }



}
