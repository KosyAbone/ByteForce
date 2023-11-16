package com.byteforce.kickash.smokeCounterWidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.byteforce.kickash.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Implementation of App Widget functionality.
 */
class SmokeCounter : AppWidgetProvider() {
    private var lastButtonClickTime: Long = 0
    private val COOLDOWN_PERIOD: Long = 3000
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context !== null) {
            if (intent?.action == "ACTION_UPDATE_WIDGET") {
                val currentTime = System.currentTimeMillis()

                if (currentTime - lastButtonClickTime >= COOLDOWN_PERIOD) {
                    lastButtonClickTime = currentTime

                    val appWidgetManager = AppWidgetManager.getInstance(context)
                    val appWidgetIds = appWidgetManager.getAppWidgetIds(
                        ComponentName(
                            context,
                            SmokeCounter::class.java
                        )
                    )
                    for (appWidgetId in appWidgetIds) {
                        updateAppWidget(context, appWidgetManager, appWidgetId)
                    }
                }
            }else {
                super.onReceive(context, intent)
            }
        }
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.smoke_counter)

    //Wait for database integration
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val recordString = dateFormat.format(Date())
    views.setTextViewText(R.id.widgetSmokeRecordDisplay, recordString)

    //Update handler
    val intent = Intent(context, SmokeCounter::class.java)
    intent.action = "ACTION_UPDATE_WIDGET"
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    views.setOnClickPendingIntent(R.id.smokeCounterAddRecButton, pendingIntent)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}

