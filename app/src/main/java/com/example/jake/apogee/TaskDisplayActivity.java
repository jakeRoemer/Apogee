package com.example.jake.apogee;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.HashMap;
import java.util.LinkedList;

public class TaskDisplayActivity extends AppCompatActivity {

    private static final String TAG = "TaskDisplayActivity";

    private EditText taskNameDisplay;
    private TextView taskDescriptionDisplay;

    private LinearLayout taskListLayout;
    private Button addTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_display);

        updateTimerThreads = new HashMap<View, timer>();

        taskNameDisplay = (EditText) findViewById(R.id.taskNameDisplay);

        taskNameDisplay.setText(getTaskName(this));

//        taskDescriptionDisplay = (TextView) findViewById(R.id.taskDescriptionDisplay);
//
//        taskDescriptionDisplay.setText(getTaskDescription(this));

        taskListLayout = (LinearLayout) findViewById(R.id.taskListLayout);

        addTaskButton = (Button) findViewById(R.id.addTaskButton);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row, null);
                TextView textOut = (TextView) addView.findViewById(R.id.taskout);
                TextView taskTimerValue = (TextView) addView.findViewById(R.id.taskTimerValue);
                ToggleButton toggleButton = (ToggleButton) addView.findViewById(R.id.timerToggleButton);
                updateTimerThreads.put(toggleButton, new timer(taskTimerValue));
                textOut.setText(taskNameDisplay.getText().toString());
                taskListLayout.addView(addView);
            }
        });

    }

    public static String getTaskName(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("shareTaskName", 0);
        return prefs.getString("taskName", "");
    }

    public static String getTaskDescription(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("shareTaskDescription", 0);
        return prefs.getString("taskDescription", "");
    }

    private HashMap<View,timer> updateTimerThreads;

    public void OnToggleClicked(View view) {
        timer t = updateTimerThreads.get(view);
        if (((ToggleButton) view).isChecked()) {
            t.setStartTime(SystemClock.uptimeMillis());
            t.getCustomHandler().postDelayed(t.getUpdateTimerThread(), 0);
        } else {
            t.setTimeSwapBuff(t.getTimeSwapBuff() + t.getTimeInMilliseconds());
            t.getCustomHandler().removeCallbacks(t.getUpdateTimerThread());
        }
    }
}
