package com.example.winterexamination;

import android.app.Application;
import android.view.View;

import com.example.winterexamination.tools.RealizeAPI;

import org.json.JSONObject;

public class MyApplication extends Application {
    private String token;
    private RealizeAPI realizeAPI;
    private int count;
    private JSONObject data;
    private String username;
    private String avatar;

    public View view[] = new View[8];

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getToken(){
        return token;
    }

    public RealizeAPI getRealizeAPI() {
        return realizeAPI;
    }

    public void setRealizeAPI(RealizeAPI realizeAPI) {
        this.realizeAPI = realizeAPI;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setToken(String str){
        token=str;
    }
}
