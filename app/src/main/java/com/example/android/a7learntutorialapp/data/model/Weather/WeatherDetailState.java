package com.example.android.a7learntutorialapp.data.model.Weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WeatherDetailState implements Parcelable {
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

    protected WeatherDetailState(Parcel in) {
        temperature = in.readFloat();
        pressure = in.readInt();
        humidity = in.readInt();
        minTemp = in.readFloat();
        maxTemp = in.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(temperature);
        dest.writeInt(pressure);
        dest.writeInt(humidity);
        dest.writeFloat(minTemp);
        dest.writeFloat(maxTemp);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<WeatherDetailState> CREATOR = new Parcelable.Creator<WeatherDetailState>() {
        @Override
        public WeatherDetailState createFromParcel(Parcel in) {
            return new WeatherDetailState(in);
        }

        @Override
        public WeatherDetailState[] newArray(int size) {
            return new WeatherDetailState[size];
        }
    };
}