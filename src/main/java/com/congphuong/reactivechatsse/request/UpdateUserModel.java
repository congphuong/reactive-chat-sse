package com.congphuong.reactivechatsse.request;

public class UpdateUserModel {
    public String url;
    public String username;

    public UpdateUserModel(){}
    public UpdateUserModel(String url, String username) {
        this.url = url;
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
