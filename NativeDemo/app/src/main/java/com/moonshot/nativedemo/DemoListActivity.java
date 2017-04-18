package com.moonshot.nativedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.moonshot.nativedemo.VuforiaSamples.app.ImageTargets.ImageTargetsActivity;
import com.moonshot.nativedemo.VuforiaSamples.app.VideoPlayback.VideoPlaybackActivity;
import com.moonshot.teapot.TeapotNativeActivity;

public class DemoListActivity extends AppCompatActivity implements DemoListActivityFragment.DemoListListener {
    private static String TAG = DemoListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public void onDemoClicked(View view, Demo demo) {
        Log.d(TAG, "onDemoClicked");
        if (TextUtils.equals("ar", demo.getType())) {
            Intent intent = ImageTargetsActivity.createDefaultIntent(this);
            startActivity(intent);
        } else if (TextUtils.equals("native", demo.getType())) {
            Intent intent = TeapotNativeActivity.createDefaultIntent(this);
            startActivity(intent);
        } else if (TextUtils.equals("accelerometer", demo.getType())) {
            Intent intent = AccelerometerGraphActivity.createDefaultIntent(this);
            startActivity(intent);
        } else if (TextUtils.equals("arvideo", demo.getType())) {
            Intent intent = VideoPlaybackActivity.createDefaultIntent(this);
            startActivity(intent);
        }



    }
}
