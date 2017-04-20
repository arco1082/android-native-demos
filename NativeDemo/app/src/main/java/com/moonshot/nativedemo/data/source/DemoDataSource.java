package com.moonshot.nativedemo.data.source;

import android.support.annotation.NonNull;

import com.moonshot.nativedemo.data.models.Demo;
import com.moonshot.nativedemo.data.models.DemoCollection;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by armando_contreras on 4/20/17.
 */

public interface DemoDataSource {

    Observable<DemoCollection> getDemoCollection();

}

