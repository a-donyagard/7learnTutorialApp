package com.example.android.a7learntutorialapp.data.repository;

import android.content.Context;
import android.widget.Toast;

import com.example.android.a7learntutorialapp.data.cloud.ApiService;
import com.example.android.a7learntutorialapp.data.cloud.RetrofitGenerator;
import com.example.android.a7learntutorialapp.data.cloud.UserDataSource;
import com.example.android.a7learntutorialapp.data.model.RegisterUser.LoginUserResponse;
import com.example.android.a7learntutorialapp.data.model.RegisterUser.RegisterUserResponse;
import com.example.android.a7learntutorialapp.data.model.RegisterUser.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpRepository {
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_FAILED = 0;
    public static final int STATUS_EMAIL_EXIST = 2;
    Context context;

    public SignUpRepository(Context context) {
        this.context = context;
    }

    public void signUpUser(UserInfo userInfo, final OnSignupComplete onSignupComplete) {

        UserDataSource userDataSource = RetrofitGenerator.getUserDataSource();
        Call<RegisterUserResponse> registerUserResponse = userDataSource.registerUser(userInfo);
        registerUserResponse.enqueue(new Callback<RegisterUserResponse>() {
            @Override
            public void onResponse(Call<RegisterUserResponse> call, retrofit2.Response<RegisterUserResponse> response) {
                if (response.isSuccessful()) {
                    onSignupComplete.onSignUp(response.body());
                }
            }

            @Override
            public void onFailure(Call<RegisterUserResponse> call, Throwable t) {
                Toast.makeText(context, "متاسفانه ثبت نام انجام نشد.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loginUser(UserInfo userInfo, final OnLoginResponse onLoginResponse) {

        UserDataSource userDataSource = RetrofitGenerator.getUserDataSource();
        Call<LoginUserResponse> loginUserResponse = userDataSource.loginUser(userInfo);
        loginUserResponse.enqueue(new Callback<LoginUserResponse>() {
            @Override
            public void onResponse(Call<LoginUserResponse> call, Response<LoginUserResponse> response) {
                if (response.isSuccessful())
                    if (response.body() != null) {
                        onLoginResponse.onResponse(response.body().isLoginResponse());
                    }
            }

            @Override
            public void onFailure(Call<LoginUserResponse> call, Throwable t) {

            }
        });
    }

    public interface OnSignupComplete {
        void onSignUp(RegisterUserResponse registerUserResponse);
    }

    public interface OnLoginResponse {
        void onResponse(boolean success);
    }
}
