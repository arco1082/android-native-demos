package com.moonshot.nativedemo.data.source;

import com.moonshot.nativedemo.data.source.local.DemoLocalDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by armando_contreras on 4/20/17.
 */

@Module
public class DemoRepositoryModule {

    @Singleton
    @Provides
    @Local
    DemoDataSource provideDemoLocalDataSource() {
        return new DemoLocalDataSource();
    }

}

