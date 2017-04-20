package com.moonshot.nativedemo.demos;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.moonshot.nativedemo.data.models.Demo;
import com.moonshot.nativedemo.data.models.DemoCollection;
import com.moonshot.nativedemo.data.source.DemoRepository;
import com.moonshot.nativedemo.rx.DefaultObserver;
import com.moonshot.nativedemo.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by armando_contreras on 4/20/17.
 */

final class DemoListPresenter implements DemoListContract.Presenter {
    private static String TAG = DemoListPresenter.class.getSimpleName();

    private final DemoRepository mDemoRepository;

    private DemoListContract.DemoView mDemosView;

    private boolean mFirstLoad = true;
    private final CompositeDisposable disposables = new CompositeDisposable();
    @NonNull
    private SchedulerProvider mSchedulerProvider;

    @Inject
    DemoListPresenter(DemoRepository repository,
                      DemoListContract.DemoView view,
                          @NonNull SchedulerProvider schedulerProvider) {
        mDemoRepository = repository;
        mDemosView = view;
        mSchedulerProvider = checkNotNull(schedulerProvider, "schedulerProvider cannot be null");
        mDemosView.setPresenter(this);
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe() {
        //loadTasks(false);
    }

    @Override
    public void unsubscribe() {
        this.mDemosView = null;
        disposables.clear();
    }

    @Inject
    void setupListeners() {
        mDemosView.setPresenter(this);
    }

    @Override
    public void loadDemos() {

        disposables.clear();
        mCurrentList.clear();

        final Observable<DemoCollection> observable = mDemoRepository
                .getDemoCollection()
                // Run on a background thread
                .subscribeOn(mSchedulerProvider.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread());

        disposables.add(observable.subscribeWith(new DemoCollectionObserver()));
    }

    private void processDemos(DemoCollection demos) {
        if (demos.isEmpty()) {
            Log.d(TAG, "processDemos is empty");
        } else {
            Log.d(TAG, "processDemos " + demos.getDemos().size());
            mDemosView.showDemoList(demos.getDemos());
        }
    }

    @Override
    public void onError() {
        mDemosView.showLoadingError();
    }

    private void processEmptyDemos() {

    }

    @Override
    public void openDemoDetails(View view, @NonNull Demo demo) {
        checkNotNull(demo, "demo cannot be null!");
        mDemosView.showDemoDetailsUI(view, demo);
    }

    private List<Demo> mCurrentList = new ArrayList<>();

    private final class DemoCollectionObserver extends DefaultObserver<DemoCollection> {

        @Override public void onComplete() {
            Log.d(TAG, "onComplete");
        }

        @Override public void onError(Throwable e) {
            Log.e(TAG, "onError", e);
            DemoListPresenter.this.onError();
        }

        @Override public void onNext(DemoCollection demo) {
            Log.d(TAG, "onNext");

            DemoListPresenter.this.processDemos(demo);
        }
    }
}