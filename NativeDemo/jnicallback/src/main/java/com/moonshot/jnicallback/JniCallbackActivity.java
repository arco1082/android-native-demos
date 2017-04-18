package com.moonshot.jnicallback;

import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by armando_contreras on 4/18/17.
 */

public class JniCallbackActivity extends AppCompatActivity {

    int hour = 0;
    int minute = 0;
    int second = 0;
    TextView tickView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jnicallback_activity);
        tickView = (TextView) findViewById(R.id.tickView);
    }
    @Override
    public void onResume() {
        super.onResume();
        hour = minute = second = 0;
        ((TextView)findViewById(R.id.hellojniMsg)).setText(stringFromJNI());
        startTicks();
    }

    @Override
    public void onPause () {
        super.onPause();
        StopTicks();
    }

    /*
     * A function calling from JNI to update current timer
     */
    @Keep
    private void updateTimer() {
        ++second;
        if(second >= 60) {
            ++minute;
            second -= 60;
            if(minute >= 60) {
                ++hour;
                minute -= 60;
            }
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String ticks = "" + JniCallbackActivity.this.hour + ":" +
                        JniCallbackActivity.this.minute + ":" +
                        JniCallbackActivity.this.second;
                JniCallbackActivity.this.tickView.setText(ticks);
            }
        });
    }
    static {
        System.loadLibrary("jnicallback");
    }
    public native  String stringFromJNI();
    public native void startTicks();
    public native void StopTicks();
}