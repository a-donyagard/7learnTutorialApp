package com.example.android.a7learntutorialapp.data.model.RegisterUser;

import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("email")
    private String userEmail;
    @SerializedName("password")
    private String userPassword;

    public UserInfo(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
