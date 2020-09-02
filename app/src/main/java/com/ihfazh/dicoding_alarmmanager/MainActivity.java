package com.ihfazh.dicoding_alarmmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvOnceDate, tvOnceTime;
    EditText etOnceMessage;
    ImageButton btnOnceDate, btnOnceTime;
    Button btnSetOnce;

    private AlarmReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOnceDate = findViewById(R.id.tv_once_date);
        tvOnceTime = findViewById(R.id.tv_once_time);
        etOnceMessage = findViewById(R.id.edit_once_message);

        btnOnceDate = findViewById(R.id.btn_once_date);
        btnOnceTime = findViewById(R.id.btn_once_time);
        btnSetOnce = findViewById(R.id.btn_set_once_button);

        btnSetOnce.setOnClickListener(this);
        btnOnceTime.setOnClickListener(this);
        btnOnceDate.setOnClickListener(this);

        receiver = new AlarmReceiver();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_once_date:
                break;
            case R.id.btn_once_time:
                break;
            case R.id.btn_set_once_button:
                break;
        }

    }
}