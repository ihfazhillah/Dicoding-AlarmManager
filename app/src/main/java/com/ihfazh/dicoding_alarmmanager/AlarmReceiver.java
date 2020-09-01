package com.ihfazh.dicoding_alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

    public void setOneTimeAlarmManager(Context context, String type, String date, String time, String message){
        String dateFormat = "yyyy-MM-dd";
        String timeFormat = "HH:mm";

        if (dateInvalid(date, dateFormat) || dateInvalid(time, timeFormat)) return;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_TYPE, type);

        Log.e("ONE TIME", date + " " + time);
        String[] dateArray = date.split("-");
        String[] timeArray = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(dateArray[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(dateArray[1]) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateArray[2]));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_ONE_TIME, intent, 0);

        if (alarmManager != null){
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }

        Toast.makeText(context, "One Time Alarm set", Toast.LENGTH_SHORT).show();

    }

    private boolean dateInvalid(String time, String timeFormat) {
        try {
            DateFormat df = new SimpleDateFormat(timeFormat, Locale.getDefault());
            df.setLenient(false);
            df.parse(time);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
