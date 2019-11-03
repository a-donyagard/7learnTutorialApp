package com.example.android.a7learntutorialapp.data.cloud;

import com.example.android.a7learntutorialapp.data.model.User;
import com.example.android.a7learntutorialapp.data.model.WeatherResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WeatherDataSource {
    @GET("data/2.5/weather")
    Call<WeatherResponse> getCurrentWeather(@Query("q") String cityName, @Query("apikey") String apiKey);

    @POST("regsiter")
    Call<ResponseBody> postUser(@Body User user);
}
