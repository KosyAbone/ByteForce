package com.byteforce.kickash.utils;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.byteforce.kickash.R;

import java.util.Random;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Show the notification

        String[] quotes = context.getResources().getStringArray(R.array.quitting_smoking_quotes);

        // Get a random quote
        String randomQuote = getRandomQuote(quotes);

        NotificationHelper.showNotification(context, "Daily Reminder", randomQuote);
    }

    private static String getRandomQuote(String[] quotes) {
        Random random = new Random();
        int index = random.nextInt(quotes.length);
        return quotes[index];
    }
}

