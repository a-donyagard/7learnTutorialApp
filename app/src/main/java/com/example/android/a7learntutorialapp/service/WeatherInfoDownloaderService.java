package com.example.android.a7learntutorialapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.android.a7learntutorialapp.ApiService;
import com.example.android.a7learntutorialapp.WeatherWidget;
import com.example.android.a7learntutorialapp.datamodel.WeatherInfo;

/**
 * Created by Saeed shahini on 11/4/2016.
 */
public class WeatherInfoDownloaderService extends Service {
    private String cityName = "Shahin Dezh";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ApiService apiService = new ApiService(this);
        apiService.getCurrentWeather(new ApiService.OnWeatherInfoReceived() {
            @Override
            public void onReceived(WeatherInfo weatherInfo) {
                if (weatherInfo != null) {
                    Intent sendDataIntent = new Intent(WeatherWidget.INTENT_ACTION_UPDATE_DATA);
                    sendDataIntent.putExtra("data", weatherInfo);
                    sendBroadcast(sendDataIntent);
                }
                stopSelf();
            }
        }, cityName);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
