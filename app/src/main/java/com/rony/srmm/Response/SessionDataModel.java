package com.rony.srmm.Response;

public class SessionDataModel {

    String accessToken;
    String userEmail;
    String password;

    public SessionDataModel(String accessToken, String userEmail, String password) {
        this.accessToken = accessToken;
        this.userEmail = userEmail;
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
