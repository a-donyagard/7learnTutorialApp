package com.example.android.a7learntutorialapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import android.util.Log;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saeed shahini on 9/8/2016.
 */
public class DownloadImageTask extends AsyncTask<Void, Integer, Void> {
    private Context context;
    private List<String> urls = new ArrayList<>();
    private ProgressDialog progressDialog;
    private static final String TAG = "DownloadImageTask";

    public DownloadImageTask(Context context, String url) {
        urls.add(url);
        this.context = context;
    }

    public DownloadImageTask(Context context, List<String> urls) {
        this.urls = urls;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("ذخیره سازی عکس ها");
        progressDialog.setMessage("در حال ذخیره سازی عکس ها، لطفاً منتظر بمانید.");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        for (int i = 0; i < urls.size(); i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                String url = urls.get(i);
                //دانلود عکس های مورد نظر توسط url ها از سرور و ذخیره در bitmap
                Bitmap bitmap = Picasso.with(context).load(url).get();

                /*
                internal storage = مکانی از حافظه که اطلاعات به صورت private ذخیره میشوند و فقط توسط خود برنامه قابل دسترسی هستند
                external storage = مکانی از حافظه که اطلاعات به صورت public ذخیره میشوند و برنامه های مختلف مثل گالری به اطلاعات مربوطه دسترسی دارند
                */
                //گرفتن آدرس دایرکتوری اپ خودمان (آدرس دایرکتوریی که باید عکسمان را در آن ذخیره کنیم)
                File extImageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                String imageName = url.substring(url.lastIndexOf("/") + 1, url.length());

                //ایجاد فایلی که عکسمون باید داخلش قرار بگیره
                File image = new File(extImageDir, imageName);
                // آماده سازی فایل ساخته شده در خط بالا جهت تزریق بایت به بایت اطلاعات (عکس) در داخل آن
                FileOutputStream fos = new FileOutputStream(image);

                //تزریق bitmap (عکس) دریافتی از سرور به صورت بایت به بایت در فایل fos یا همان فایل image
                if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos))
                {
                    Log.i(TAG, "doInBackground: file is created");
                }
                fos.flush();
                fos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            publishProgress(((i+1) * 100) / urls.size());

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }

}
