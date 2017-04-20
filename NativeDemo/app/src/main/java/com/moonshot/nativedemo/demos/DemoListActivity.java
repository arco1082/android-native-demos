package com.moonshot.nativedemo.demos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.moonshot.googlevr.TreasureHuntActivity;
import com.moonshot.graph.AccelerometerGraphActivity;
import com.moonshot.jnicallback.JniCallbackActivity;
import com.moonshot.nativedemo.NativeDemosApplication;
import com.moonshot.nativedemo.R;
import com.moonshot.nativedemo.data.models.Demo;
import com.moonshot.vuforiaar.VuforiaSamples.app.ImageTargets.ImageTargetsActivity;
import com.moonshot.vuforiaar.VuforiaSamples.app.VideoPlayback.VideoPlaybackActivity;
import com.moonshot.teapot.TeapotNativeActivity;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class DemoListActivity extends AppCompatActivity implements DemoListActivityFragment.DemoListListener {
    private static String TAG = DemoListActivity.class.getSimpleName();
    @Inject
    DemoListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_demo_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DemoListActivityFragment fragment =
                (DemoListActivityFragment) getSupportFragmentManager().findFragmentById(R.id.main_content);

        if (fragment == null) {
            // Create the fragment
            fragment = DemoListActivityFragment.createNew();
            checkNotNull(getSupportFragmentManager());
            checkNotNull(fragment);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_content, fragment);
            transaction.commit();
        }

        DaggerDemoListComponent.builder()
                .demoRepositoryComponent(((NativeDemosApplication) getApplication()).getDemoRepositoryComponent())
                .demoPresenterModule(new DemoPresenterModule(fragment)).build()
                .inject(this);

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
        } else if (TextUtils.equals("googlevr", demo.getType())) {
            Intent intent = TreasureHuntActivity.createDefaultIntent(this);
            startActivity(intent);
        }else if (TextUtils.equals("jnicallback", demo.getType())) {
            Intent intent = JniCallbackActivity.createDefaultIntent(this);
            startActivity(intent);
        }



    }
}
