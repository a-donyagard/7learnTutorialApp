package com.example.android.a7learntutorialapp.view.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.android.a7learntutorialapp.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageButton btnBack = (ImageButton) findViewById(R.id.back_button);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button editAvatar = (Button) findViewById(R.id.edit_avatar);
        editAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "ویرایش عکس کلیک شد", Toast.LENGTH_SHORT).show();
            }
        });

        //اعمال فونت
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/yekan.ttf");
        EditText firstNameEditText = (EditText) findViewById(R.id.edittext_firstName);
        firstNameEditText.setTypeface(typeface);

        EditText lastNameEditText = (EditText) findViewById(R.id.edittext_lastName);
        lastNameEditText.setTypeface(typeface);

        //استفاده از event های اصلی view ها
        firstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Toast.makeText(ProfileActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        CheckBox javaCheckBox = (CheckBox) findViewById(R.id.java_checkbox);
        CheckBox cssCheckBox = (CheckBox) findViewById(R.id.css_checkbox);
        CheckBox htmlCheckBox = (CheckBox) findViewById(R.id.html_checkbox);

        javaCheckBox.setTypeface(typeface);
        cssCheckBox.setTypeface(typeface);
        htmlCheckBox.setTypeface(typeface);

        javaCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(ProfileActivity.this, String.valueOf(isChecked), Toast.LENGTH_SHORT).show();
            }
        });

        RadioButton maleradio = (RadioButton) findViewById(R.id.male_radio);
        RadioButton femaleradio = (RadioButton) findViewById(R.id.female_radio);

        maleradio.setTypeface(typeface);
        femaleradio.setTypeface(typeface);

        maleradio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(ProfileActivity.this, String.valueOf(isChecked), Toast.LENGTH_SHORT).show();
            }
        });

        Button saveForm = (Button) findViewById(R.id.save_form);
        saveForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "save form is clicked", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
