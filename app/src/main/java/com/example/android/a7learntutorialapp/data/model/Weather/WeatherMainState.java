package com.example.android.a7learntutorialapp.data.model.Weather;

import com.google.gson.annotations.SerializedName;

public class WeatherMainState {

    @SerializedName("id")
    private final int id;
    @SerializedName("main")
    private final String main;
    @SerializedName("description")
    private final String description;

    public WeatherMainState(int id, String main, String description) {
        this.id = id;
        this.main = main;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }
}
