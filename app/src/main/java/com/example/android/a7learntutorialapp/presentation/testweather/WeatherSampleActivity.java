package com.example.android.a7learntutorialapp.presentation.testweather;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.a7learntutorialapp.R;
import com.example.android.a7learntutorialapp.data.cloud.RetrofitGenerator;
import com.example.android.a7learntutorialapp.data.cloud.WeatherDataSource;
import com.example.android.a7learntutorialapp.data.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeatherSampleActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView txtWeatherName;
    private TextView txtWeatherDescription;
    private TextView txtTemp;
    private TextView txtHumidity;
    private TextView txtPressure;
    private TextView txtMinTemp;
    private TextView txtMaxTemp;
    private TextView txtWindSpeed;
    private TextView txtWindDegree;
    private WeatherDataSource weatherDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_sample);
        weatherDataSource = RetrofitGenerator.getWeatherDataSource();
        initViews();

        Button btnSendRequest = (Button) findViewById(R.id.btn_send_request);
        btnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Call<WeatherResponse> call = weatherDataSource.getCurrentWeather("Tehran", "0067ea3ffc9cad0548529afa3639f76f");
                call.enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                        progressBar.setVisibility(View.GONE);
                        if (response.isSuccessful()) {
                            WeatherResponse weatherResponse = response.body();
                            onReceived(weatherResponse);
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    private void initViews() {
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        txtWeatherName = (TextView) findViewById(R.id.txt_weather_name);
        txtWeatherDescription = (TextView) findViewById(R.id.txt_weather_description);
        txtTemp = (TextView) findViewById(R.id.txt_temprature);
        txtHumidity = (TextView) findViewById(R.id.txt_humidity);
        txtPressure = (TextView) findViewById(R.id.txt_pressure);
        txtMinTemp = (TextView) findViewById(R.id.txt_min_temp);
        txtMaxTemp = (TextView) findViewById(R.id.txt_max_temp);
        txtWindSpeed = (TextView) findViewById(R.id.txt_wind_speed);
        txtWindDegree = (TextView) findViewById(R.id.txt_wind_degree);


    }

    public void onReceived(WeatherResponse weatherResponse) {
        if (weatherResponse != null) {
            //show information to user
            txtWeatherName.setText(String.format("آب و هوای فعلی: %s", weatherResponse.getCityName()));
//            txtWeatherDescription.setText("توضیحات: ");
            txtTemp.setText(String.format("دمای فعلی: %s", weatherResponse.getCityWeather().getTemperature()));
            txtHumidity.setText(String.format("رطوبت هوا: %s", String.valueOf(weatherResponse.getCityWeather().getHumidity())));
            txtPressure.setText(String.format("میزان فشار هوا: %s", String.valueOf(weatherResponse.getCityWeather().getPressure())));
            txtMinTemp.setText(String.format("کم ترین دما: %s", String.valueOf(weatherResponse.getCityWeather().getMinTemp())));
            txtMaxTemp.setText(String.format("بیشترین دما: %s", String.valueOf(weatherResponse.getCityWeather().getMaxTemp())));
//            txtWindSpeed.setText(String.format("سرعت باد: %s", String.valueOf(weatherInfo.getWindSpeed())));
//            txtWindDegree.setText(String.format("درجه ی باد: %s", String.valueOf(weatherInfo.getWindDegree())));
        } else {
            Toast.makeText(this, "خطا در دریافت اطلاعات", Toast.LENGTH_LONG).show();
        }
        progressBar.setVisibility(View.INVISIBLE);
    }

    /*
         ********** How to use Gson *************
        Gson gson = new Gson();
        String json = gson.toJson(weatherResponse);

        WeatherResponse wr = gson.fromJson(json, WeatherResponse.class);

        */
}
