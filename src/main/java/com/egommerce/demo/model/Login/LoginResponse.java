package com.egommerce.demo.model.Login;

public class LoginResponse {

    private String token;
    private String errorMessage;

    public LoginResponse(String token) {
        this.token = token;
    }

    public LoginResponse(String token, String errorMessage) {
        this.token = token;
        this.errorMessage = errorMessage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
