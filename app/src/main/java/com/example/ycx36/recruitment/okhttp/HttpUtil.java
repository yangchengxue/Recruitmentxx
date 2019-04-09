package com.example.ycx36.recruitment.okhttp;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    public static void sendPersonalMessage(String address, String headURI, String name, String sex, String email, String phone, okhttp3.Callback callback) {
        String params = "headURI=" + headURI + "&name=" + name + "&sex=" + sex + "&email=" + email + "&phone=" + phone;
        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=utf-8"),
                params);

        //向服务器发送Post
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendUserInfo(String address, String userName, String realName, String birthday, String nation, String sex,
                                    String title, String birthplace, String highestEducation,
                                    String highestOffering, String politicsStatus,
                                    String graduateSchool, okhttp3.Callback callback) {
        String params = "realName=" + realName + "&userName=" + userName + "&birthday=" + birthday + "&nation=" + nation + "&sex=" + sex +
                "&title=" + title + "&birthplace=" + birthplace + "&highestEducation=" + highestEducation +
                "&highestOffering=" + highestOffering + "&politicsStatus=" + politicsStatus + "&graduateSchool=" + graduateSchool;
        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=utf-8"),
                params);

        //向服务器发送Post
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendWorkExperience(String address, String userName, String companyName, String positionName,
                                          String time, String workContent, String workPerformance, okhttp3.Callback callback) {
        String params = "userName=" + userName + "&companyName=" + companyName + "&positionName=" + positionName + "&time=" + time
                + "&workContent=" + workContent + "&workPerformance=" + workPerformance;
        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=utf-8"),
                params);

        //向服务器发送Post
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendProjectExperience(String address, String userName, String projectName, String role,
                                          String time, String projectSynopsis, String projectPerformance, okhttp3.Callback callback) {
        String params = "userName=" + userName + "&projectName=" + projectName + "&role=" + role + "&time=" + time
                + "&projectSynopsis=" + projectSynopsis + "&projectPerformance=" + projectPerformance;
        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=utf-8"),
                params);

        //向服务器发送Post
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendEducationExperience(String address, String userName, String schoolName, String major,
                                             String time, String awards, String highestEducation, okhttp3.Callback callback) {
        String params = "userName=" + userName + "&schoolName=" + schoolName + "&major=" + major + "&time=" + time
                + "&awards=" + awards + "&highestEducation=" + highestEducation;
        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=utf-8"),
                params);

        //向服务器发送Post
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
