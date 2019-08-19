package com.gedgonz.platzikgram;

import android.app.Application;

import com.facebook.FacebookSdk;

public class PlatzikgramApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
