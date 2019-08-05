package com.msdemorn;

import android.app.Activity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class MinischoolViewModule extends ReactContextBaseJavaModule {

    public MinischoolViewModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override
    public String getName() {
        return "MinischoolViewModule";
    }

    public Activity getActivity() {
        return this.getCurrentActivity();
    }

}
