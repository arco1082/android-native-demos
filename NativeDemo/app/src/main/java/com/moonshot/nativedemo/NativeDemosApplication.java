package com.moonshot.nativedemo;

import android.app.Application;

import com.moonshot.nativedemo.data.source.DaggerDemoRepositoryComponent;
import com.moonshot.nativedemo.data.source.DemoRepositoryComponent;
import com.moonshot.nativedemo.di.ApplicationModule;

/**
 * Created by armando_contreras on 4/20/17.
 */

public class NativeDemosApplication extends Application {

    private DemoRepositoryComponent mRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.initializeInjector();
    }

    protected void initializeInjector() {
        mRepositoryComponent = DaggerDemoRepositoryComponent.builder()
                .applicationModule(new ApplicationModule((NativeDemosApplication) getApplicationContext()))
                .build();
    }

    public DemoRepositoryComponent getDemoRepositoryComponent() {
        return mRepositoryComponent;
    }


}
