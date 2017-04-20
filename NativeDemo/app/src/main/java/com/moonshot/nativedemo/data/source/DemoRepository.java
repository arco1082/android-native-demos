package com.moonshot.nativedemo.data.source;

import android.support.annotation.NonNull;

import com.moonshot.nativedemo.data.models.Demo;
import com.moonshot.nativedemo.data.models.DemoCollection;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by armando_contreras on 4/20/17.
 */

@Singleton
public class DemoRepository implements DemoDataSource {

    private final DemoDataSource mDemoLocalDataSource;

    @Inject
    DemoRepository(@Local DemoDataSource demoLocalDataSource) {
        mDemoLocalDataSource = demoLocalDataSource;
    }

    @Override
    public Observable<DemoCollection> getDemoCollection() {
        return mDemoLocalDataSource
                .getDemoCollection();

    }


}


