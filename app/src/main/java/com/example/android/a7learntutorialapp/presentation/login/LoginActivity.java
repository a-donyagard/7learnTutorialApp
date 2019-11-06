package com.example.android.a7learntutorialapp.presentation.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.android.a7learntutorialapp.data.cloud.ApiService;
import com.example.android.a7learntutorialapp.R;
import com.example.android.a7learntutorialapp.data.local.UserSharedPrefManager;
import com.example.android.a7learntutorialapp.data.model.RegisterUser.UserInfo;
import com.example.android.a7learntutorialapp.data.model.RegisterUser.RegisterUserResponse;
import com.example.android.a7learntutorialapp.data.repository.SignUpRepository;
import com.example.android.a7learntutorialapp.presentation.main.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoginActivity extends AppCompatActivity {

    private Button changeAuthenticationState;
    private boolean isSigningUp = false;
    private SignUpViewModel mSignUpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupViews();
    }

    private void setupViews() {
        final EditText emailEditText = (EditText) findViewById(R.id.edit_text_email);
        final EditText passwordEditText = (EditText) findViewById(R.id.edit_text_password);

        final TextView authenticationTextView = (TextView) findViewById(R.id.label_authentication);
        final FloatingActionButton authenticateButton = (FloatingActionButton) findViewById(R.id.float_button_authenticate);
        changeAuthenticationState = (Button) findViewById(R.id.button_authentication);
        changeAuthenticationState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSigningUp = !isSigningUp;
                if (isSigningUp) {
                    authenticationTextView.setText(getString(R.string.label_sign_up));
                    changeAuthenticationState.setText(getString(R.string.label_login));
                    authenticateButton.setImageResource(R.drawable.ic_action_sign_up);
                } else {
                    authenticationTextView.setText(getString(R.string.label_login));
                    changeAuthenticationState.setText(getString(R.string.label_sign_up));
                    authenticateButton.setImageResource(R.drawable.ic_action_login);
                }
            }
        });


        authenticateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                UserInfo userInfo = new UserInfo(email,password);
                final UserSharedPrefManager sharedPrefManager = new UserSharedPrefManager(LoginActivity.this);
                mSignUpViewModel = ViewModelProviders.of(LoginActivity.this).get(SignUpViewModel.class);
                if (isSigningUp) {
                    if (!email.isEmpty() && !password.isEmpty()) {
                        if (password.length() >= 4) {
                            if (isEmailValid(email)) {
                                mSignUpViewModel.signUpUser(userInfo);
                                mSignUpViewModel.getRegisterUserResponse().observe(LoginActivity.this, new Observer<RegisterUserResponse>() {
                                    @Override
                                    public void onChanged(RegisterUserResponse registerUserResponse) {
                                        switch (registerUserResponse.getRegisterResponse()) {
                                            case SignUpRepository.STATUS_EMAIL_EXIST:
                                                Toast.makeText(LoginActivity.this, "کاربری با این ایمیل موجود است.", Toast.LENGTH_SHORT).show();
                                                break;
                                            case SignUpRepository.STATUS_FAILED:
                                                Toast.makeText(LoginActivity.this, "متاسفانه ثبت نام انجام نشد.", Toast.LENGTH_SHORT).show();
                                                break;
                                            case SignUpRepository.STATUS_SUCCESS:
                                                Toast.makeText(LoginActivity.this, "ثبت نام با موفقیت انجام شد", Toast.LENGTH_SHORT).show();

                                                sharedPrefManager.saveUserLoginInfo(email);
                                                Intent resultIntent = new Intent();
                                                resultIntent.putExtra("email", email);
                                                setResult(MainActivity.RESULT_OK, resultIntent);
                                                finish();
                                                break;
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(LoginActivity.this, "ایمیل معتبر نیست.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "طول پسورد باید حداقل 4 کاراکتر باشد.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "ایمیل و پسورد نمی توانند خالی باشند.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (!email.isEmpty() && !password.isEmpty()) {
                        mSignUpViewModel.loginUser(userInfo);
                        mSignUpViewModel.getLoginUserResponse().observe(LoginActivity.this, new Observer<Boolean>() {
                            @Override
                            public void onChanged(Boolean success) {
                                if (success) {
                                    Toast.makeText(LoginActivity.this, "خوش آمدید", Toast.LENGTH_SHORT).show();
                                    sharedPrefManager.saveUserLoginInfo(email);
                                    Intent resultIntent = new Intent();
                                    resultIntent.putExtra("email", email);
                                    setResult(MainActivity.RESULT_OK, resultIntent);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "اطلاعات صحیح نیست", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(LoginActivity.this, "ایمیل و پسورد نمی توانند خالی باشند.", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
