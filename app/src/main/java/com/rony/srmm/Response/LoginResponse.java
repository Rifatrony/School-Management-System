package com.rony.srmm.Response;

public class LoginResponse {

    public boolean success;
    public String message;
    public String token;

    public LoginResponse(boolean success, String message, String token) {
        this.success = success;
        this.message = message;
        this.token = token;
    }
}
