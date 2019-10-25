package com.example.android.a7learntutorialapp.view.activity;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.a7learntutorialapp.R;
import com.example.android.a7learntutorialapp.SevenLearnDatabaseOpenHelper;
import com.squareup.picasso.Picasso;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


//        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/yekan.ttf");
        Intent intent = getIntent();

        int id = intent.getIntExtra(SevenLearnDatabaseOpenHelper.COL_ID, 0);

        setPostVisited(id);

        String title = intent.getStringExtra(SevenLearnDatabaseOpenHelper.COL_TITLE);
        String content = intent.getStringExtra(SevenLearnDatabaseOpenHelper.COL_CONTENT);
        String postImageUrl = intent.getStringExtra(SevenLearnDatabaseOpenHelper.COL_POST_IMAGE_URL);
        String date = intent.getStringExtra(SevenLearnDatabaseOpenHelper.COL_DATE);

        ImageView postImageView = (ImageView) findViewById(R.id.item_image);
        TextView titleTextView = (TextView) findViewById(R.id.posts_title);
        TextView contentTextView = (TextView) findViewById(R.id.posts_content);
        TextView dateTextView = (TextView) findViewById(R.id.posts_date);

        Picasso.with(this).load(postImageUrl.replace("localhost", "192.168.1.106")).into(postImageView);
        titleTextView.setText(title);
        contentTextView.setText(content);
        dateTextView.setText(date);

//        titleTextView.setTypeface(typeface);
//        contentTextView.setTypeface(typeface);
//        dateTextView.setTypeface(typeface);
    }

    private void setPostVisited(int postId) {
        SevenLearnDatabaseOpenHelper databaseOpenHelper = new SevenLearnDatabaseOpenHelper(this);
        databaseOpenHelper.setPostIsVisited(postId, 1);
    }
}
