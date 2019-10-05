package com.example.android.a7learntutorialapp.datamodel;

/**
 * Created by Saeed shahini on 8/11/2016.
 */
public class WeatherInfo {
    private String weatherName;
    private String weatherDescription;
    private float windSpeed;
    private float windDegree;
    private int humidity;
    private float weatherTemprature;
    private float minTemprature;
    private float maxTemprature;
    private int pressure;

    public String getWeatherName() {
        return weatherName;
    }

    public void setWeatherName(String weatherName) {
        this.weatherName = weatherName;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(float windDegree) {
        this.windDegree = windDegree;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getWeatherTemprature() {
        return weatherTemprature;
    }

    public void setWeatherTemprature(float weatherTemprature) {
        this.weatherTemprature = weatherTemprature;
    }

    public float getMinTemprature() {
        return minTemprature;
    }

    public void setMinTemprature(float minTemprature) {
        this.minTemprature = minTemprature;
    }

    public float getMaxTemprature() {
        return maxTemprature;
    }

    public void setMaxTemprature(float maxTemprature) {
        this.maxTemprature = maxTemprature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }
}
