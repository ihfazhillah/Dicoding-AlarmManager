package com.ihfazh.dicoding_alarmmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener{

    TextView tvOnceDate, tvOnceTime, tvRepeatTime;
    EditText etOnceMessage, etRepeatMessage;
    ImageButton btnOnceDate, btnOnceTime, btnRepeatTime;
    Button btnSetOnce, btnSetRepeat;

    private AlarmReceiver receiver;

    final String DATE_PICKER_TAG = "DatePicker";
    final String TIME_PICKER_ONCE_TAG = "TimePickerOnce";
    final String TIME_PICKER_REPEAT_TAG = "TimePickerRepeat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOnceDate = findViewById(R.id.tv_once_date);
        tvOnceTime = findViewById(R.id.tv_once_time);
        tvRepeatTime = findViewById(R.id.tv_repeat_time);
        etOnceMessage = findViewById(R.id.edit_once_message);
        etRepeatMessage = findViewById(R.id.edit_repeat_message);

        btnOnceDate = findViewById(R.id.btn_once_date);
        btnOnceTime = findViewById(R.id.btn_once_time);
        btnRepeatTime = findViewById(R.id.btn_repeat_time);
        btnSetOnce = findViewById(R.id.btn_set_once_button);
        btnSetRepeat = findViewById(R.id.btn_set_repeat_button);

        btnSetRepeat.setOnClickListener(this);
        btnRepeatTime.setOnClickListener(this);
        btnSetOnce.setOnClickListener(this);
        btnOnceTime.setOnClickListener(this);
        btnOnceDate.setOnClickListener(this);

        receiver = new AlarmReceiver();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_once_date:
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), DATE_PICKER_TAG);
                break;
            case R.id.btn_once_time:
                TimePickerFragment timePickerFragmentOne = new TimePickerFragment();
                timePickerFragmentOne.show(getSupportFragmentManager(), TIME_PICKER_ONCE_TAG);
                break;
            case R.id.btn_repeat_time:
                TimePickerFragment timePickerFragmentRepeat = new TimePickerFragment();
                timePickerFragmentRepeat.show(getSupportFragmentManager(), TIME_PICKER_REPEAT_TAG);
                break;
            case R.id.btn_set_once_button:
                String onceDate = tvOnceDate.getText().toString();
                String onceTime = tvOnceTime.getText().toString();
                String message = etOnceMessage.getText().toString();

                receiver.setOneTimeAlarmManager(
                        this,
                        AlarmReceiver.TYPE_ONE_TIME,
                        onceDate,
                        onceTime,
                        message
                );
                break;
            case R.id.btn_set_repeat_button:
                String repeatTime = tvRepeatTime.getText().toString();
                String repeatMessage = etRepeatMessage.getText().toString();

                receiver.setRepeatAlarmManager(
                        this,
                        AlarmReceiver.TYPE_REPEAT,
                        repeatTime,
                        repeatMessage
                );

        }

    }

    @Override
    public void onDialogDateSet(String tag, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        tvOnceDate.setText(
                dateFormat.format(calendar.getTime())
        );
    }

    @Override
    public void onTimeSet(String tag, int hour, int minute) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        switch (tag){
            case TIME_PICKER_ONCE_TAG:
                tvOnceTime.setText(dateFormat.format(calendar.getTime()));
                break;
            case TIME_PICKER_REPEAT_TAG:
                tvRepeatTime.setText(dateFormat.format(calendar.getTime()));
                break;
            default:
                break;
        }

    }
}