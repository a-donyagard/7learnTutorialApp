package com.example.android.a7learntutorialapp.presentation.weatherwidget;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.android.a7learntutorialapp.data.cloud.ApiService;
import com.example.android.a7learntutorialapp.data.cloud.RetrofitGenerator;
import com.example.android.a7learntutorialapp.data.cloud.WeatherDataSource;
import com.example.android.a7learntutorialapp.data.model.Weather.WeatherInfo;
import com.example.android.a7learntutorialapp.data.model.Weather.WeatherResponse;
import com.example.android.a7learntutorialapp.data.repository.WeatherRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

        WeatherRepository weatherRepository = new WeatherRepository();
        weatherRepository.getCurrentWeather(cityName, new WeatherRepository.OnWeatherInfoReceived() {
            @Override
            public void onReceived(WeatherResponse weatherResponse) {
                if (weatherResponse != null) {
                    Intent sendDataIntent = new Intent(WeatherWidget.INTENT_ACTION_UPDATE_DATA);
                    sendDataIntent.putExtra("data", weatherResponse);
                    sendBroadcast(sendDataIntent);
                }
                stopSelf();
            }
        });


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        this.unregisterReceiver(weatherWidget);
        super.onDestroy();
    }
}
