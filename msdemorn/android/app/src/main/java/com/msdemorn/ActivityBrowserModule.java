package com.msdemorn;

import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class ActivityBrowserModule extends ReactContextBaseJavaModule {

    ActivityBrowserModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override
    public String getName() {
        return "ActivityBrowser";
    }

    @ReactMethod
    void start(String url) {
        Activity activity = getCurrentActivity();

        if(activity != null) {
            Intent intent = new Intent(activity, BrowserActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("url", url);
            activity.startActivity(intent);
        }
    }
}
