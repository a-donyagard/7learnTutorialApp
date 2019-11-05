package com.example.android.a7learntutorialapp.data.cloud;

import com.example.android.a7learntutorialapp.data.model.RegisterUser.LoginUserResponse;
import com.example.android.a7learntutorialapp.data.model.RegisterUser.UserInfo;
import com.example.android.a7learntutorialapp.data.model.RegisterUser.RegisterUserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserDataSource {
    @POST("7learn/SaveUser.php")
    Call<RegisterUserResponse> registerUser(@Body UserInfo userInfo);

    @POST("7learn/LoginUser.php")
    Call<LoginUserResponse> loginUser(@Body UserInfo userInfo);
}
