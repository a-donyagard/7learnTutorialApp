package com.example.android.a7learntutorialapp.view.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.a7learntutorialapp.R;
import com.example.android.a7learntutorialapp.UserSharedPrefManager;
import com.example.android.a7learntutorialapp.datamodel.User;

public class ProfileActivity extends AppCompatActivity {
    private User user = new User();
    private UserSharedPrefManager prefManager;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        prefManager = new UserSharedPrefManager(this);
        user = prefManager.getUser();

        Button editAvatar = (Button) findViewById(R.id.edit_avatar);
        editAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "ویرایش عکس کلیک شد", Toast.LENGTH_SHORT).show();
            }
        });

        //اعمال فونت
        typeface = Typeface.createFromAsset(getAssets(), "fonts/yekan.ttf");
        EditText firstNameEditText = (EditText) findViewById(R.id.edittext_firstName);
//        firstNameEditText.setTypeface(typeface);
        firstNameEditText.setText(user.getFirstName());

        EditText lastNameEditText = (EditText) findViewById(R.id.edittext_lastName);
//        lastNameEditText.setTypeface(typeface);
        lastNameEditText.setText(user.getLastName());

        //استفاده از event های اصلی view ها
        firstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Toast.makeText(ProfileActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                user.setFirstName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                user.setLastName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        CheckBox javaCheckBox = (CheckBox) findViewById(R.id.java_checkbox);
        CheckBox cssCheckBox = (CheckBox) findViewById(R.id.css_checkbox);
        CheckBox htmlCheckBox = (CheckBox) findViewById(R.id.html_checkbox);

        javaCheckBox.setChecked(user.isJavaExpert());
        cssCheckBox.setChecked(user.isCssExpert());
        htmlCheckBox.setChecked(user.isHtmlExpert());

//        javaCheckBox.setTypeface(typeface);
//        cssCheckBox.setTypeface(typeface);
//        htmlCheckBox.setTypeface(typeface);

        htmlCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                user.setHtmlExpert(isChecked);
            }
        });

        cssCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                user.setCssExpert(isChecked);
            }
        });

        javaCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                user.setJavaExpert(isChecked);
            }
        });

        RadioButton maleRadio = (RadioButton) findViewById(R.id.male_radio);
        RadioButton femaleRadio = (RadioButton) findViewById(R.id.female_radio);

//        maleRadio.setTypeface(typeface);
//        femaleRadio.setTypeface(typeface);

        byte gender=user.getGender();
        if (gender==User.MALE){
            maleRadio.setChecked(true);
        }else {
            femaleRadio.setChecked(true);
        }

        maleRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    user.setGender(User.MALE);
                }else {
                    user.setGender(User.FEMALE);
                }
            }
        });

        Button saveForm = (Button) findViewById(R.id.save_form);
        saveForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManager.saveUser(user);
                Toast.makeText(ProfileActivity.this, "save form is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        setupToolbar();
    }

    private void setupToolbar(){
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        for (int i = 0; i < toolbar.getChildCount(); i++) {
//            if (toolbar.getChildAt(i) instanceof TextView){
//                ((TextView) toolbar.getChildAt(i)).setTypeface(typeface);
//            }
//        }
    }
}
