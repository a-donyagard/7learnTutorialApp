package com.example.android.a7learntutorialapp.data.cloud;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.android.a7learntutorialapp.data.local.PostEntity;
import com.example.android.a7learntutorialapp.data.model.RegisterUser.LoginUserResponse;
import com.example.android.a7learntutorialapp.data.model.RegisterUser.UserInfo;
import com.example.android.a7learntutorialapp.data.model.RegisterUser.RegisterUserResponse;
import com.example.android.a7learntutorialapp.data.model.Weather.WeatherInfo;
import com.example.android.a7learntutorialapp.data.model.Weather.WeatherResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApiService {
    private static final String TAG = "ApiService";
    private Context context;
    private WeatherDataSource weatherDataSource;


    public ApiService(Context context) {
        this.context = context;
    }

    public void getCurrentWeather(final OnWeatherInfoReceived onWeatherInfoReceived, String cityName) {

        weatherDataSource = RetrofitGenerator.getWeatherDataSource();
        Call<WeatherResponse> call = weatherDataSource.getCurrentWeather(cityName, "0067ea3ffc9cad0548529afa3639f76f");
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, retrofit2.Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    onWeatherInfoReceived.onReceived(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e(TAG, "onFailureResponse: " + t.toString());
            }
        });


        /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&apikey=0067ea3ffc9cad0548529afa3639f76f",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: " + response.toString());
                onWeatherInfoReceived.onReceived(parseResponseToWeatherInfo(response));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: " + error.toString());
                onWeatherInfoReceived.onReceived(null);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(8000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);*/
    }


    private WeatherInfo parseResponseToWeatherInfo(JSONObject response) {
        WeatherInfo weatherInfo = new WeatherInfo();
        try {
            JSONArray weatherJsonArray = response.getJSONArray("weather");
            JSONObject weatherJsonObject = weatherJsonArray.getJSONObject(0);
            weatherInfo.setWeatherId(weatherJsonObject.getInt("id"));
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

        PostsDataSource postsDataSource = RetrofitGenerator.getPostsDataSource();
        Call<List<PostEntity>> posts = postsDataSource.getPosts();
        posts.enqueue(new Callback<List<PostEntity>>() {
            @Override
            public void onResponse(Call<List<PostEntity>> call, retrofit2.Response<List<PostEntity>> response) {
                if(response.isSuccessful()){
                    onPostsReceived.onReceived(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<PostEntity>> call, Throwable t) {

            }
        });


        /*JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, "http://192.168.1.106/7learn/getposts.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i(TAG, "onResponse: " + response.toString());

                List<PostEntity> posts = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    PostEntity post = new PostEntity();
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
        Volley.newRequestQueue(context).add(request);*/
    }


    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_FAILED = 0;
    public static final int STATUS_EMAIL_EXIST = 2;

    public void signUpUser(UserInfo userInfo, final OnSignupComplete onSignupComplete) {

        UserDataSource userDataSource = RetrofitGenerator.getUserDataSource();
        Call<RegisterUserResponse> registerUserResponse = userDataSource.registerUser(userInfo);
        registerUserResponse.enqueue(new Callback<RegisterUserResponse>() {
            @Override
            public void onResponse(Call<RegisterUserResponse> call, retrofit2.Response<RegisterUserResponse> response) {
                if (response.isSuccessful()){
                    onSignupComplete.onSignUp(response.body());
                }
            }

            @Override
            public void onFailure(Call<RegisterUserResponse> call, Throwable t) {
                Toast.makeText(context, "متاسفانه ثبت نام انجام نشد.", Toast.LENGTH_SHORT).show();
            }
        });


        /*JSONObject requestJsonObject = new JSONObject();
        try {
            requestJsonObject.put("email", email);
            requestJsonObject.put("password", password);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                    "http://192.168.1.106/7learn/SaveUser.php", requestJsonObject,
                    new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        int responseStatus = response.getInt("response");
                        onSignupComplete.onSignUp(responseStatus);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    onSignupComplete.onSignUp(STATUS_FAILED);
                }
            });

            request.setRetryPolicy(new DefaultRetryPolicy(18000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(context).add(request);

        } catch (JSONException e) {
            Log.e(TAG, "signUpUser: " + e.toString());
        }*/

    }

    public void loginUser(UserInfo userInfo, final OnLoginResponse onLoginResponse) {

        UserDataSource userDataSource = RetrofitGenerator.getUserDataSource();
        Call<LoginUserResponse> loginUserResponse = userDataSource.loginUser(userInfo);
        loginUserResponse.enqueue(new Callback<LoginUserResponse>() {
            @Override
            public void onResponse(Call<LoginUserResponse> call, Response<LoginUserResponse> response) {
                if(response.isSuccessful())
                    if (response.body() != null) {
                        onLoginResponse.onResponse(response.body().isLoginResponse());
                    }
            }

            @Override
            public void onFailure(Call<LoginUserResponse> call, Throwable t) {

            }
        });


        /*JSONObject requestJsonObject = new JSONObject();
        try {
            requestJsonObject.put("email", email);
            requestJsonObject.put("password", password);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://192.168.1.106/7learn/LoginUser.php", requestJsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        boolean success = response.getBoolean("response");
                        onLoginResponse.onResponse(success);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            request.setRetryPolicy(new DefaultRetryPolicy(18000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(context).add(request);
        } catch (JSONException e) {
            Log.e(TAG, "loginUser: " + e.toString());
        }*/
    }

    public interface OnWeatherInfoReceived {
        void onReceived(WeatherResponse weatherResponse);
    }

    public interface OnPostsReceived {
        void onReceived(List<PostEntity> posts);
    }

    public interface OnSignupComplete {
        void onSignUp(RegisterUserResponse registerUserResponse);
    }

    public interface OnLoginResponse {
        void onResponse(boolean success);
    }
}
