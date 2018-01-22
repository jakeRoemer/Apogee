package com.example.jake.apogee;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CountdownActivity extends AppCompatActivity {

    private Button backToMainButton;

    public int counter;
    Button countdownButton;
    TextView countdownValue;

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

        countdownValue = (TextView) findViewById(R.id.countdownValue);

        countdownButton = (Button) findViewById(R.id.countdownButton);

        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(30000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        countdownValue.setText(String.valueOf(counter));
                        counter++;
                    }
                    public void onFinish() {
                        countdownValue.setText("FINISH!");
                    }
                }.start();
            }
        });
    }
}
