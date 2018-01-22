package com.example.jake.apogee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button timerActivityButton;
    private Button alarmActivityButton;
    private Button countdownActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerActivityButton = (Button) findViewById(R.id.timerActivityButton);

        timerActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTimerActivity();
            }
        });

        alarmActivityButton = (Button) findViewById(R.id.alarmActivityButton);

        alarmActivityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                launchAlarmActivity();
            }
        });

        countdownActivityButton = (Button) findViewById(R.id.countdownActivityButton);

        countdownActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCountdownActivity();
            }
        });
    }

    private void launchTimerActivity() {
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }

    private void launchAlarmActivity() {
        Intent intent = new Intent(this, AlarmActivity.class);
        startActivity(intent);
    }

    private void launchCountdownActivity() {
        Intent intent = new Intent(this, CountdownActivity.class);
        startActivity(intent);
    }
}
