package com.example.android.a7learntutorialapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.a7learntutorialapp.datamodel.Post;
import com.example.android.a7learntutorialapp.datamodel.WeatherInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saeed shahini on 8/11/2016.
 */
public class ApiService {
    private static final String TAG = "ApiService";
    private Context context;


    public ApiService(Context context) {
        this.context = context;
    }

    public void getCurrentWeather(final OnWeatherInfoRecieved onWeatherInfoRecieved, String cityName) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "https://api.openweathermap.org/data/2.5/weather?q=London&apikey=0067ea3ffc9cad0548529afa3639f76f",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: " + response.toString());
                onWeatherInfoRecieved.onRecieved(parseResponseToWeatherInfo(response));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: " + error.toString());
                onWeatherInfoRecieved.onRecieved(null);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(8000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }


    private WeatherInfo parseResponseToWeatherInfo(JSONObject response) {
        WeatherInfo weatherInfo = new WeatherInfo();
        try {
            JSONArray weatherJsonArray = response.getJSONArray("weather");
            JSONObject weatherJsonObject = weatherJsonArray.getJSONObject(0);
            weatherInfo.setWeatherName(weatherJsonObject.getString("main"));
            weatherInfo.setWeatherDescription(weatherJsonObject.getString("description"));
            JSONObject mainJsonObject = response.getJSONObject("main");
            weatherInfo.setWeatherTemprature((float) mainJsonObject.getDouble("temp"));
            weatherInfo.setHumidity(mainJsonObject.getInt("humidity"));
            weatherInfo.setPressure(mainJsonObject.getInt("pressure"));
            weatherInfo.setMinTemprature((float) mainJsonObject.getDouble("temp_min"));
            weatherInfo.setMaxTemprature((float) mainJsonObject.getDouble("temp_max"));

            JSONObject windJsonObject = response.getJSONObject("wind");
            weatherInfo.setWindSpeed((float) windJsonObject.getDouble("speed"));
            weatherInfo.setWindDegree((float) windJsonObject.getDouble("deg"));

            return weatherInfo;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void getPosts(final OnPostsReceived onPostsReceived) {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, "http://192.168.1.106/7learn/getposts.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i(TAG, "onResponse: "+response.toString());

                List<Post> posts = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    Post post = new Post();
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        post.setTitle(jsonObject.getString("title"));
                        post.setId(jsonObject.getInt("id"));
                        post.setContent(jsonObject.getString("content"));
                        post.setPostImageUrl(jsonObject.getString("image_url"));
                        post.setDate(jsonObject.getString("date"));

                        posts.add(post);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                onPostsReceived.onReceived(posts);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: " + error);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(18000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }


    public interface OnWeatherInfoRecieved {
        void onRecieved(WeatherInfo weatherInfo);
    }

    public interface OnPostsReceived {
        void onReceived(List<Post> posts);
    }
}
