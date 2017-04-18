package com.moonshot.jnicallback;

import android.os.Build;
import android.support.annotation.Keep;
import android.util.Log;

/**
 * Created by armando_contreras on 4/18/17.
 */

public class JniHandler {
    /*
     * Print out status to logcat
     */
    @Keep
    private void updateStatus(String msg) {
        if (msg.toLowerCase().contains("error")) {
            Log.e("JniHandler", "Native Err: " + msg);
        } else {
            Log.i("JniHandler", "Native Msg: " + msg);
        }
    }

    /*
     * Return OS build version: a static function
     */
    @Keep
    static public String getBuildVersion() {
        return Build.VERSION.RELEASE;
    }

    /*
     * Return Java memory info
     */
    @Keep
    public long getRuntimeMemorySize() {
        return Runtime.getRuntime().freeMemory();
    }

}