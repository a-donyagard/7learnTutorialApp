package com.example.android.a7learntutorialapp.animations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.android.a7learntutorialapp.R;


public class AnimationsMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations_main);

        setupToolbar();
        setupButtons();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setupButtons() {
        Button alphaAnimationButton = (Button) findViewById(R.id.button_alpha_animation);
        alphaAnimationButton.setOnClickListener(this);

        Button translateAnimationButton = (Button) findViewById(R.id.button_translate_animation);
        translateAnimationButton.setOnClickListener(this);

        Button scaleAnimationButton = (Button) findViewById(R.id.button_scale_animation);
        scaleAnimationButton.setOnClickListener(this);

        Button rotateAnimationButton = (Button) findViewById(R.id.button_rotate_animation);
        rotateAnimationButton.setOnClickListener(this);

        Button valueAnimatorAnimationButton = (Button) findViewById(R.id.button_value_animator);
        valueAnimatorAnimationButton.setOnClickListener(this);

        Button animationSetButton=(Button)findViewById(R.id.button_animation_set);
        animationSetButton.setOnClickListener(this);

        Button animationsYoyoButton=(Button)findViewById(R.id.button_yoyo);
        animationsYoyoButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, AnimationActivity.class);
        intent.putExtra(AnimationActivity.EXTRA_KEY_ANIMATION_TYPE,
                Integer.parseInt((String) view.getTag()));
        startActivity(intent);
    }
}
