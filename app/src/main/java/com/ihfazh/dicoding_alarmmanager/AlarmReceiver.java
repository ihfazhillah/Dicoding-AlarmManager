package com.ihfazh.dicoding_alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String TYPE_ONE_TIME = "type one time";
    public static final String TYPE_REPEAT = "type repeat";
    public static final String EXTRA_MESSAGE = "extra message";
    public static final String EXTRA_TYPE = "extra type";


    private final int ID_ONE_TIME = 100;
    private final int ID_REPEATING = 101;

    @Override
    public void onReceive(Context context, Intent intent) {
        String type = intent.getStringExtra(EXTRA_TYPE);
        String message = intent.getStringExtra(EXTRA_MESSAGE);

        String title = type.equalsIgnoreCase(TYPE_ONE_TIME) ? TYPE_ONE_TIME : TYPE_REPEAT;
        int notifId = type.equalsIgnoreCase(TYPE_ONE_TIME) ? ID_ONE_TIME : ID_REPEATING;

        showToast(context, title, message);
    }

    private void showToast(Context context, String title, String message) {
        Toast.makeText(context, title + " : " + message, Toast.LENGTH_LONG).show();
    }
}
