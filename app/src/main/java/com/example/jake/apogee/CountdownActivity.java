package com.example.jake.apogee;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CountdownActivity extends AppCompatActivity {

    private static final String TAG = "CountdownActivity";

    private Button backToMainButton;
    private Button countdownButton;
    private NumberPicker setHours;
    private NumberPicker setMinutes;
    private NumberPicker setSeconds;

    private long startHours = 0L;
    private long startMinutes = 0L;
    private long startSeconds = 0L;

    private Date event = null;
    private String EVENT_DATE_TIME = "2018-01-22 22:30:00";
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private LinearLayout linear_layout_1, linear_layout_2;
    private TextView tv_days, tv_hour, tv_minute, tv_second;
    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        backToMainButton = (Button) findViewById(R.id.backToMainButton);

        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setHours = (NumberPicker) findViewById(R.id.setHours);

        setHours.setMinValue(0);
        setHours.setMaxValue(99);
        setHours.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int i) {
                startHours = numberPicker.getValue();
            }
        });

        setMinutes = (NumberPicker) findViewById(R.id.setMinutes);

        setMinutes.setMinValue(0);
        setMinutes.setMaxValue(59);
        setMinutes.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int i) {
                startMinutes = numberPicker.getValue();
            }
        });

        setSeconds = (NumberPicker) findViewById(R.id.setSeconds);

        setSeconds.setMinValue(0);
        setSeconds.setMaxValue(59);
        setSeconds.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int i) {
                startSeconds = numberPicker.getValue();
            }
        });

        countdownButton = (Button) findViewById(R.id.countdownButton);

        countdownButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                initUI();
                countDownStart();
            }
        });
    }
    private void initUI() {
        linear_layout_1 = findViewById(R.id.linear_layout_1);
        linear_layout_2 = findViewById(R.id.linear_layout_2);
        tv_days = findViewById(R.id.tv_days);
        tv_hour = findViewById(R.id.tv_hour);
        tv_minute = findViewById(R.id.tv_minute);
        tv_second = findViewById(R.id.tv_second);
    }

    private void countDownStart() {
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
//                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
//                    Date event_date = dateFormat.parse(EVENT_DATE_TIME);
                    Date current_date = new Date();
                    if (event == null) {
                        event = new Date();
                        event.setTime(current_date.getTime()+(startHours*60*60*1000));
                        event.setTime(event.getTime()+(startMinutes*60*1000));
                        event.setTime(event.getTime()+(startSeconds*1000));
                    }
                    if (!current_date.after(event)) {
                        long diff = event.getTime() - current_date.getTime();
                        long Days = diff / (24* 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;
                        tv_days.setText(String.format("%02d", Days));
                        tv_hour.setText(String.format("%02d", Hours));
                        tv_minute.setText(String.format("%02d", Minutes));
                        tv_second.setText(String.format("%02d", Seconds));
                    } else {
                        linear_layout_1.setVisibility(View.VISIBLE);
                        linear_layout_2.setVisibility(View.GONE);
                        handler.removeCallbacks(runnable);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }
}
