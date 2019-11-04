package com.example.android.a7learntutorialapp.data.model.Weather;

import com.google.gson.annotations.SerializedName;

public class WeatherWind {
    @SerializedName("speed")
    private final float speed;
    @SerializedName("deg")
    private final float degree;

    public WeatherWind(float speed, float degree) {
        this.speed = speed;
        this.degree = degree;
    }

    public float getSpeed() {
        return speed;
    }

    public float getDegree() {
        return degree;
    }
}
