package com.moonshot.nativedemo.rx;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

/**
 * Created by armando_contreras on 4/20/17.
 */

public interface BaseSchedulerProvider {

    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

}
