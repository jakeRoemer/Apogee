package com.example.jake.apogee;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button timerActivityButton;
    private Button alarmActivityButton;
    private Button countdownActivityButton;
    private Button taskActivityButton;
    private Button taskDisplayActivityButton;
    private Button nutritionActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerActivityButton = (Button) findViewById(R.id.timerActivityButton);

        timerActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity(TimerActivity.class);
            }
        });

        alarmActivityButton = (Button) findViewById(R.id.alarmActivityButton);

        alarmActivityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                launchActivity(AlarmActivity.class);
            }
        });

        countdownActivityButton = (Button) findViewById(R.id.countdownActivityButton);

        countdownActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity(CountdownActivity.class);
            }
        });

        taskActivityButton = (Button) findViewById(R.id.taskActivityButton);

        taskActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity(TaskActivity.class);
           }
        });

        taskDisplayActivityButton = (Button) findViewById(R.id.taskDisplayActivityButton);

        taskDisplayActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity(TaskDisplayActivity.class);
            }
        });

        nutritionActivityButton = (Button) findViewById(R.id.nutriionActivityButton);

        nutritionActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity(NutritionActivity.class);
            }
        });
    }

    private void launchActivity(Class activity_class) {
        Intent intent = new Intent(this, activity_class);
        startActivity(intent);
    }
}
