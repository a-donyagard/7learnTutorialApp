package com.example.android.a7learntutorialapp.data.model.Weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WeatherMainState implements Parcelable {

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

    protected WeatherMainState(Parcel in) {
        id = in.readInt();
        main = in.readString();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(main);
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<WeatherMainState> CREATOR = new Parcelable.Creator<WeatherMainState>() {
        @Override
        public WeatherMainState createFromParcel(Parcel in) {
            return new WeatherMainState(in);
        }

        @Override
        public WeatherMainState[] newArray(int size) {
            return new WeatherMainState[size];
        }
    };
}