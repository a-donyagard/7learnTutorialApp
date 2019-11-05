package com.example.android.a7learntutorialapp.data.cloud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitGenerator {

    private static Retrofit weatherRetrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
            .build();

    private static Retrofit myApiRetrofitBuilder = new Retrofit.Builder()
            .baseUrl("http://192.168.1.106/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
            .build();

    public static WeatherDataSource getWeatherDataSource() {
        return weatherRetrofitBuilder.create(WeatherDataSource.class);
    }

    public static PostsDataSource getPostsDataSource(){
        return myApiRetrofitBuilder.create(PostsDataSource.class);
    }

    public static UserDataSource getUserDataSource(){
        return myApiRetrofitBuilder.create(UserDataSource.class);
    }
}
