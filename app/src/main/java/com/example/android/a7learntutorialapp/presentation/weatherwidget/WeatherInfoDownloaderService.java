package com.example.android.a7learntutorialapp.presentation.weatherwidget;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.android.a7learntutorialapp.data.cloud.ApiService;
import com.example.android.a7learntutorialapp.data.model.WeatherInfo;


public class WeatherInfoDownloaderService extends Service {
    private String cityName = "Shahin Dezh";
    private WeatherWidget weatherWidget = new WeatherWidget();

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
        IntentFilter filter = new IntentFilter();
        filter.addAction(WeatherWidget.INTENT_ACTION_UPDATE_DATA);
        this.registerReceiver(weatherWidget,filter);

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
        this.unregisterReceiver(weatherWidget);
        super.onDestroy();
    }
}
