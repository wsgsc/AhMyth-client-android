package com.king.ahh.abcc.manager;

import android.content.Context;

import com.king.ahh.abcc.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by gsc on 18-9-11.
 */

public class HostsManager {

    private static final String URL_GIT = "https://github.com/mikeandcc/AbcTest/blob/master/README.md";
    private static final String SIGN = "@@@###";
    private static OkHttpClient mOkHttpClient = new OkHttpClient();


    public static  final String [] getHostsByResource(Context context) {
        String hosts []= context.getResources().getStringArray(R.array.hosts);
        return hosts;
    }

    public static final String getHostsByGit() {
        String host = null;
        Request request = new Request.Builder().url(URL_GIT).build();
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            if(response.isSuccessful()) {
                host = toHost(response.body().string());
                if(host != null) {
                    host = EncryManager.decipher(host);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  host;
    }

    private static String toHost(String str) {
        /* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(SIGN);
        int strEndIndex = str.lastIndexOf(SIGN);

        /* index 为负数 即表示该字符串中 没有该字符 */
        if (strStartIndex < 0) {
            return null;
        }
        if (strEndIndex < 0) {
            return null;
        }
        /* 开始截取 */
        String result = str.substring(strStartIndex, strEndIndex).substring(SIGN.length());
        return result;
    }




}
