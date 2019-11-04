package com.example.android.a7learntutorialapp.data.model.Weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {
    @SerializedName("weather")
    private final List<WeatherMainState> weatherMainState;
    @SerializedName("main")
    private final WeatherDetailState weatherDetailState;
    @SerializedName("wind")
    private final WeatherWind weatherWind;

    public WeatherResponse(List<WeatherMainState> weatherMainState,
                           WeatherDetailState weatherDetailState, WeatherWind weatherWind) {
        this.weatherMainState = weatherMainState;
        this.weatherDetailState = weatherDetailState;
        this.weatherWind = weatherWind;
    }

    public List<WeatherMainState> getWeatherMainState() {
        return weatherMainState;
    }

    public WeatherDetailState getWeatherDetailState() {
        return weatherDetailState;
    }

    public WeatherWind getWeatherWind() {
        return weatherWind;
    }
}
