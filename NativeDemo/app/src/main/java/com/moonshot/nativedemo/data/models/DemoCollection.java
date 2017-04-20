package com.moonshot.nativedemo.data.models;

import android.util.Log;

import java.util.List;

/**
 * Created by armando_contreras on 4/20/17.
 */

public class DemoCollection {
    private List<Demo> mDemos;


    public DemoCollection(List<Demo> demos) {
        this.mDemos = demos;
    }

    public List<Demo> getDemos() {
        return mDemos;
    }

    public boolean isEmpty() {
        Log.d("DemoCollection", "isEmpty");
        if (mDemos == null) {
            return true;
        }
        Log.d("DemoCollection", "isEmpty " + mDemos.size());
        return mDemos.size() == 0;
    }

}
