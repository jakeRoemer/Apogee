package com.example.jake.apogee;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

/**
 * Created by jake on 2/6/18.
 */

public class timer {

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    private long startTime = 0L;

    public Handler getCustomHandler() {
        return customHandler;
    }

    public void setCustomHandler(Handler customHandler) {
        this.customHandler = customHandler;
    }

    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public void setTimeInMilliseconds(long timeInMilliseconds) {
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public long getTimeSwapBuff() {
        return timeSwapBuff;
    }

    public void setTimeSwapBuff(long timeSwapBuff) {
        this.timeSwapBuff = timeSwapBuff;
    }

    long timeSwapBuff = 0L;

    public long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }

    long updatedTime = 0L;

    TextView taskTimerValue;

    public timer (TextView taskTimerValue) {
        this.taskTimerValue = taskTimerValue;
    }

    public Runnable getUpdateTimerThread() {
        return updateTimerThread;
    }

    public void setUpdateTimerThread(Runnable updateTimerThread) {
        this.updateTimerThread = updateTimerThread;
    }

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            taskTimerValue.setText("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }
    };
}
