package com.example.android.a7learntutorialapp.presentation.weatherwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.android.a7learntutorialapp.R;
import com.example.android.a7learntutorialapp.data.model.Weather.WeatherInfo;

/**
 * Implementation of App Widget functionality.
 */
public class WeatherWidget extends AppWidgetProvider {
    public String cityName = "Shahin Dezh";
    public static final String INTENT_ACTION_UPDATE_DATA = "com.example.android.a7learntutorialapp.UPDATE_DATA";

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId, WeatherInfo weatherInfo, String cityName) {
        // Construct the RemoteViews object
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.weather_widget);
        remoteViews.setTextViewText(R.id.text_weather_temp, String.valueOf((int) (weatherInfo.getWeatherTemprature() - 273.15)) + "\u00b0");
        remoteViews.setTextViewText(R.id.text_city_name, cityName);

        switch (weatherInfo.getWeatherId() / 100) {
            case 2:
                remoteViews.setImageViewResource(R.id.icon_weather, R.drawable.thunderstorm);
                break;
            case 3:
                remoteViews.setImageViewResource(R.id.icon_weather, R.drawable.drizzle);
                break;
            case 5:
                remoteViews.setImageViewResource(R.id.icon_weather, R.drawable.rain);
                break;
            case 6:
                remoteViews.setImageViewResource(R.id.icon_weather, R.drawable.snow);
                break;
            case 7:
                remoteViews.setImageViewResource(R.id.icon_weather, R.drawable.mist);
                break;
            case 8:
                remoteViews.setImageViewResource(R.id.icon_weather, R.drawable.clear_sky);
                break;
            default:
                remoteViews.setImageViewResource(R.id.icon_weather, R.drawable.clear_sky);
        }
        if (weatherInfo.getWeatherId() / 10 == 80)
            remoteViews.setImageViewResource(R.id.icon_weather, R.drawable.clouds);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        context.startService(new Intent(context, WeatherInfoDownloaderService.class));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equals(INTENT_ACTION_UPDATE_DATA)) {
            WeatherInfo weatherInfo = intent.getParcelableExtra("data");
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, WeatherWidget.class));

            for (int i = 0; i < appWidgetIds.length; i++) {
                updateAppWidget(context, appWidgetManager, appWidgetIds[i], weatherInfo, cityName);
            }
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

