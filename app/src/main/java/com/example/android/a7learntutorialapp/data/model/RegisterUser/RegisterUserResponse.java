package com.example.android.a7learntutorialapp.data.model.RegisterUser;

import com.google.gson.annotations.SerializedName;

public class RegisterUserResponse {
    @SerializedName("response")
    private int registerResponse;

    public RegisterUserResponse(int registerResponse) {
        this.registerResponse = registerResponse;
    }

    public int getRegisterResponse() {
        return registerResponse;
    }
}
