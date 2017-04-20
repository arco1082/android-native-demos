package com.moonshot.nativedemo.rx;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by armando_contreras on 4/20/17.
 */

public class DefaultObserver<T> extends DisposableObserver<T> {
    @Override public void onNext(T t) {
        // no-op by default.
    }

    @Override public void onComplete() {
        // no-op by default.
    }

    @Override public void onError(Throwable exception) {
        // no-op by default.
    }
}