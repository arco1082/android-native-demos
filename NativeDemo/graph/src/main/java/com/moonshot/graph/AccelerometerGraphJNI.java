package com.moonshot.graph;

import android.content.res.AssetManager;

/**
 * Created by armando_contreras on 4/18/17.
 */

public class AccelerometerGraphJNI {

    static {
        System.loadLibrary("accelerometergraph");
    }

    public static native void init(AssetManager assetManager);
    public static native void surfaceCreated();
    public static native void surfaceChanged(int width, int height);
    public static native void drawFrame();
    public static native void pause();
    public static native void resume();
}
