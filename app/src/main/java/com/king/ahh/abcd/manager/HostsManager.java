package com.king.ahh.abcd.manager;

import android.content.Context;

import com.king.ahh.abcd.R;

/**
 * Created by gsc on 18-9-11.
 */

public class HostsManager {
    public static  final String [] getHosts(Context context) {
        String hosts []= context.getResources().getStringArray(R.array.hosts);
        return hosts;
    }
}
