package com.example.android.a7learntutorialapp.data.model.RegisterUser;

import com.google.gson.annotations.SerializedName;

public class LoginUserResponse {
    @SerializedName("response")
    private boolean loginResponse;

    public LoginUserResponse(boolean loginResponse) {
        this.loginResponse = loginResponse;
    }

    public boolean isLoginResponse() {
        return loginResponse;
    }
}
