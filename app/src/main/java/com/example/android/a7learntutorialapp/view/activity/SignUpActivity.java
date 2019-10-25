package com.example.android.a7learntutorialapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.android.a7learntutorialapp.ApiService;
import com.example.android.a7learntutorialapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText firstNameEditText = (EditText) findViewById(R.id.text_first_name);
        final EditText lastNameEditText = (EditText) findViewById(R.id.text_last_name);
        final EditText ageEditText = (EditText) findViewById(R.id.text_age);

        final CheckBox cssCheckBox = (CheckBox) findViewById(R.id.css_checkbox);
        final CheckBox htmlCheckBox = (CheckBox) findViewById(R.id.html_checkbox);
        final CheckBox javaCheckBox = (CheckBox) findViewById(R.id.java_checkbox);

        final RadioButton maleRadio = (RadioButton) findViewById(R.id.male_radio);
        RadioButton femaleRadio = (RadioButton) findViewById(R.id.female_radio);

        final SwitchCompat hasJobSwitchCompat = (SwitchCompat) findViewById(R.id.has_job_switch);

        Button signUpButton = (Button) findViewById(R.id.button_signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiService apiService = new ApiService(SignUpActivity.this);

                JSONObject requestJsonObject = new JSONObject();
                try {
                    requestJsonObject.put("first_name", firstNameEditText.getText().toString());
                    requestJsonObject.put("last_name", lastNameEditText.getText().toString());
                    requestJsonObject.put("has_job", hasJobSwitchCompat.isChecked());
                    requestJsonObject.put("age", ageEditText.getText().toString());
                    if (maleRadio.isChecked()) {
                        requestJsonObject.put("gender", "male");
                    } else {
                        requestJsonObject.put("gender", "female");
                    }

                    JSONArray skillsJsonArray = new JSONArray();
                    if (cssCheckBox.isChecked()) {
                        skillsJsonArray.put("Css");
                    }

                    if (htmlCheckBox.isChecked()) {
                        skillsJsonArray.put("Html");
                    }

                    if (javaCheckBox.isChecked()) {
                        skillsJsonArray.put("java");
                    }
                    requestJsonObject.put("skills", skillsJsonArray);


                    apiService.signUpUser(requestJsonObject, new ApiService.OnSignupComplete() {
                        @Override
                        public void onSignUp(boolean success) {
                            if (success) {
                                Toast.makeText(SignUpActivity.this, "ثبت نام با موفقیت انجام شد.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(SignUpActivity.this, "ثبت نام انجام نشد، دوباره سعی کنید!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
