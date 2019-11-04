package com.example.android.a7learntutorialapp.data.model.Weather;

import com.google.gson.annotations.SerializedName;

public class WeatherDetailState {
    @SerializedName("temp")
    private final float temperature;
    @SerializedName("pressure")
    private final int pressure;
    @SerializedName("humidity")
    private final int humidity;
    @SerializedName("temp_min")
    private final float minTemp;
    @SerializedName("temp_max")
    private final float maxTemp;

    public WeatherDetailState(float temperature, int pressure, int humidity, float minTemp, float maxTemp) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public float getTemperature() {
        return temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }
}
