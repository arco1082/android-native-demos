package com.moonshot.nativedemo.demos;

import dagger.Module;
import dagger.Provides;

/**
 * Created by armando_contreras on 4/20/17.
 */

@Module
public class DemoPresenterModule {

    private final DemoListContract.DemoView mView;

    public DemoPresenterModule(DemoListContract.DemoView view) {
        mView = view;
    }

    @Provides
    DemoListContract.DemoView provideDemoListView() {
        return mView;
    }

}

