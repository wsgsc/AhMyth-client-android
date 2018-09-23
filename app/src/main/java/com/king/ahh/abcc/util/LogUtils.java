package com.king.ahh.abcc.util;

import android.util.Log;

/**
 * Created by gsc on 18-9-7.
 */

public class LogUtils {
    private static final String BASELOG =  "ahmyth";
    private static final boolean ISLOGGED = true;
    public static void log(String TAG, String log) {
        if(ISLOGGED) Log.d(BASELOG + ":" +TAG,log);
    }
}
