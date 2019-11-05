package com.example.android.a7learntutorialapp.data.model.Weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherResponse implements Parcelable {

    @SerializedName("id")
    private final int weatherId;
    @SerializedName("weather")
    private final List<WeatherMainState> weatherMainState;
    @SerializedName("main")
    private final WeatherDetailState weatherDetailState;
    @SerializedName("wind")
    private final WeatherWind weatherWind;

    public WeatherResponse(int weatherId, List<WeatherMainState> weatherMainState,
                           WeatherDetailState weatherDetailState, WeatherWind weatherWind) {
        this.weatherId = weatherId;
        this.weatherMainState = weatherMainState;
        this.weatherDetailState = weatherDetailState;
        this.weatherWind = weatherWind;
    }

    public int getWeatherId() {
        return weatherId;
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

    protected WeatherResponse(Parcel in) {
        weatherId = in.readInt();
        if (in.readByte() == 0x01) {
            weatherMainState = new ArrayList<WeatherMainState>();
            in.readList(weatherMainState, WeatherMainState.class.getClassLoader());
        } else {
            weatherMainState = null;
        }
        weatherDetailState = (WeatherDetailState) in.readValue(WeatherDetailState.class.getClassLoader());
        weatherWind = (WeatherWind) in.readValue(WeatherWind.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(weatherId);
        if (weatherMainState == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(weatherMainState);
        }
        dest.writeValue(weatherDetailState);
        dest.writeValue(weatherWind);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<WeatherResponse> CREATOR = new Parcelable.Creator<WeatherResponse>() {
        @Override
        public WeatherResponse createFromParcel(Parcel in) {
            return new WeatherResponse(in);
        }

        @Override
        public WeatherResponse[] newArray(int size) {
            return new WeatherResponse[size];
        }
    };
}