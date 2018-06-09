package com.congphuong.reactivechatsse.entity;

import java.util.List;

public class RegisterRequest {
    String username;
    String password;
    List<String> roles;
    boolean enabled;

    public RegisterRequest(){}
    public RegisterRequest(String username, String password, List<String> roles, boolean enabled) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
