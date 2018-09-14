package com.king.ahh.abcd.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by gsc on 18-9-7.
 */

public class ToastUtils {
    public static void toast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
