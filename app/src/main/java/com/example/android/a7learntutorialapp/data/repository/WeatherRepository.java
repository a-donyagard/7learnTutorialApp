package com.example.android.a7learntutorialapp.data.repository;

import android.util.Log;

import com.example.android.a7learntutorialapp.data.cloud.RetrofitGenerator;
import com.example.android.a7learntutorialapp.data.cloud.WeatherDataSource;
import com.example.android.a7learntutorialapp.data.model.Weather.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class WeatherRepository {
    private static final String TAG = "ApiService";

    public void getCurrentWeather(String cityName, final OnWeatherInfoReceived onWeatherInfoReceived) {

        WeatherDataSource weatherDataSource = RetrofitGenerator.getWeatherDataSource();
        Call<WeatherResponse> call = weatherDataSource.getCurrentWeather(cityName, "0067ea3ffc9cad0548529afa3639f76f");
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, retrofit2.Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    onWeatherInfoReceived.onReceived(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e(TAG, "onFailureResponse: " + t.toString());
            }
        });
    }

    public interface OnWeatherInfoReceived {
        void onReceived(WeatherResponse weatherResponse);
    }
}
