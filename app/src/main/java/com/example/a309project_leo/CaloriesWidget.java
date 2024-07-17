package com.example.a309project_leo;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class CaloriesWidget extends AppWidgetProvider {
    private static final String PREFS_NAME = "MyAppPreferences";
    private static final String KEY_CAL = "key_calories";
    private static final String KEY_GOAL_CAL = "key_goal_calories";


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Resources res = context.getResources();
        int currentCalories = res.getInteger(R.integer.currentCalories);
        int goalCalories = res.getInteger(R.integer.goalCalories);

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.calories_widget);
        views.setTextViewText(R.id.appwidget_text, "Calories:" +
                sharedPreferences.getInt(KEY_CAL,0)+ "\nGoal:" + sharedPreferences.getInt(KEY_GOAL_CAL,0));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
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