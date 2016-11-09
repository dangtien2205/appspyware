package com.example.tienbi.appspyware;

import android.app.Application;
import android.content.Context;

/**
 * Created by TienBi on 13/09/2016.
 */
public class App extends Application {
    private static Context context;
    private static String key;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        key=null;
    }
    public static Context getContext(){
        return context;
    }

    public static void setKey(String key1) {
        key = key1;
    }

    public static String getKey() {
        return key;
    }
}
