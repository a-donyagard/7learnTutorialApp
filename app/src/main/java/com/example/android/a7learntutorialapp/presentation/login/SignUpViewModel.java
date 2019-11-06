package com.example.android.a7learntutorialapp.presentation.login;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.a7learntutorialapp.data.model.RegisterUser.RegisterUserResponse;
import com.example.android.a7learntutorialapp.data.model.RegisterUser.UserInfo;
import com.example.android.a7learntutorialapp.data.repository.SignUpRepository;

public class SignUpViewModel extends AndroidViewModel {
    SignUpRepository signUpRepository;
    Context context;
    private MutableLiveData<RegisterUserResponse> registerUser = new MutableLiveData<>();
    private MutableLiveData<Boolean> loginUserResponse = new MutableLiveData<>();

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        signUpRepository = new SignUpRepository(context);
    }

    public void signUpUser(UserInfo userInfo) {
        signUpRepository.signUpUser(userInfo, new SignUpRepository.OnSignupComplete() {
            @Override
            public void onSignUp(RegisterUserResponse registerUserResponse) {
                registerUser.setValue(registerUserResponse);
            }
        });
    }

    public LiveData<RegisterUserResponse> getRegisterUserResponse(){
        return registerUser;
    }

    public void loginUser(UserInfo userInfo){
        signUpRepository.loginUser(userInfo, new SignUpRepository.OnLoginResponse() {
            @Override
            public void onResponse(boolean success) {
                loginUserResponse.setValue(success);
            }
        });
    }

    public LiveData<Boolean> getLoginUserResponse(){
        return loginUserResponse;
    }
}
