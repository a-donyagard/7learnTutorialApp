package com.example.android.a7learntutorialapp.data.model.Weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WeatherWind implements Parcelable {
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

    protected WeatherWind(Parcel in) {
        speed = in.readFloat();
        degree = in.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(speed);
        dest.writeFloat(degree);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<WeatherWind> CREATOR = new Parcelable.Creator<WeatherWind>() {
        @Override
        public WeatherWind createFromParcel(Parcel in) {
            return new WeatherWind(in);
        }

        @Override
        public WeatherWind[] newArray(int size) {
            return new WeatherWind[size];
        }
    };
}