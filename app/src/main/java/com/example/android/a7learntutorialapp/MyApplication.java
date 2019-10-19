package com.example.android.a7learntutorialapp;

import android.app.Application;
import android.content.Intent;
import android.graphics.Typeface;

/**
 * Created by Saeed shahini on 9/23/2016.
 */
public class MyApplication extends Application {
    private static Typeface iranianSansFont;

    @Override
    public void onCreate() {
        super.onCreate();
//        iranianSansFont=Typeface.createFromAsset(getAssets(),"fonts/iranian_sans.ttf");

        Intent intent = new Intent("com.example.sshahini.myapplication.test");
        intent.putExtra("message_content", "Hello");
        sendBroadcast(intent);
    }

//    public static Typeface getIranianSansFont(){
//        return iranianSansFont;
//    }
}
