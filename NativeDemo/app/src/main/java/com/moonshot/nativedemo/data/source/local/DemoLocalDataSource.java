package com.moonshot.nativedemo.data.source.local;

import android.support.annotation.NonNull;
import android.util.Log;

import com.moonshot.nativedemo.data.models.Demo;
import com.moonshot.nativedemo.data.models.DemoCollection;
import com.moonshot.nativedemo.data.source.DemoDataSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableToList;

/**
 * Created by armando_contreras on 4/20/17.
 */

@Singleton
public class DemoLocalDataSource implements DemoDataSource {
    private static String TAG = DemoLocalDataSource.class.getSimpleName();

    public DemoLocalDataSource() {
    }

    @Override
    public Observable<DemoCollection> getDemoCollection() {
        Log.d(TAG, "getFollowerList");
        ArrayList<Demo> items = new ArrayList<>();
        items.add(new Demo("Open GL NativeActivity", "This is an exmple of using dependent native modules to create a native view using OpenGL. The module is injectec in the manifest by extending NativeActivity. In project, its \"teapot\" module", "native", "https://learningscenejs.files.wordpress.com/2010/03/teapot-example1.jpg"));
        items.add(new Demo("Open GL Graph GlView", "This is an exmple module that creates a graph of accelerometer data. In project, its \"graph\" module", "accelerometer", "http://www.larsivar.com/cp/images/android_AccForces.png"));
        items.add(new Demo("JNI Non-Static Callback", "Load JNI C++ module and make non static calls into an Activity. In project, its \"jnicallback\" module", "jnicallback", "http://helicaltech.com/wp-content/uploads/2014/12/jni.png"));
        items.add(new Demo("Teapot AR Vuforia", "This is an example of using the Vuforia SDK to launch an Augmented Reality Exprience. Use of Virtual Button functionality. In project, its \"vuforiaar\" module", "ar", "https://learningscenejs.files.wordpress.com/2010/03/teapot-example1.jpg"));
        items.add(new Demo("Video AR Vuforia", "This is an exmple module using Vuforia SDK to render a video on a target. In project, its \"vuforiaar\" module", "arvideo", "http://answers.unity3d.com/storage/temp/81928-screenshot-2016-11-10-14-46-59.png"));
        items.add(new Demo("Google VR SDK", "This is an exmple module loading Google VR SDK", "googlevr", "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTkVtwsAPNSrFCZNZMvr74UyuW6ZlxiuQ4eYvRHqDtT3tLKNAY3utqXZJP_"));

        DemoCollection collection = new DemoCollection(items);
        Observable<DemoCollection> obs = Observable.just(collection);
        return obs;
    }


}

