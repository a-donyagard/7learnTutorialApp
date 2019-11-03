package com.example.android.a7learntutorialapp.data.model;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("id")
    private final int cityId;
    @SerializedName("name")
    private final String cityName;
    @SerializedName("timezone")
    private final int timezone;
    @SerializedName("main")
    private final CityWeather cityWeather;

    public WeatherResponse(int cityId, String cityName, int timezone, CityWeather cityWeather) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.timezone = timezone;
        this.cityWeather = cityWeather;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public int getTimezone() {
        return timezone;
    }

    public CityWeather getCityWeather() {
        return cityWeather;
    }

}
