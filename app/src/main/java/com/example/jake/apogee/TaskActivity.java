package com.example.jake.apogee;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TaskActivity extends AppCompatActivity {

    private Button backToMainButton;

    private EditText taskText;
    private EditText descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        backToMainButton = (Button) findViewById(R.id.backToMainButton);

        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        taskText = (EditText) findViewById(R.id.taskText);

        taskText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_NEXT) {
                    setTaskName(TaskActivity.this, textView.getText().toString());
                }
                return handled;
            }
        });

        descriptionText = (EditText) findViewById(R.id.descriptionText);

        descriptionText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_DONE) {
                    setTaskDescription(TaskActivity.this, textView.getText().toString());

                    //Close Keyboard
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    handled = true;

                    //Launch Task Display Activity
                    launchActivity(TaskDisplayActivity.class);
                }
                return handled;
            }
        });
    }

    public static void setTaskName(Context context, String taskName) {
        SharedPreferences prefs = context.getSharedPreferences("shareTaskName", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("taskName", taskName);
        editor.commit();
    }

    public static void setTaskDescription(Context context, String taskDescription) {
        SharedPreferences prefs = context.getSharedPreferences("shareTaskDescription", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("taskDescription", taskDescription);
        editor.commit();
    }

    private void launchActivity(Class activity_class) {
        Intent intent = new Intent(this, activity_class);
        startActivity(intent);
    }
}
