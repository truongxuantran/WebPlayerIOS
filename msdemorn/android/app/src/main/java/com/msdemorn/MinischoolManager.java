package com.msdemorn;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

import javax.annotation.Nullable;

public class MinischoolManager extends SimpleViewManager<MinischoolView> {
    public static final String REACT_CLASS = "MinischoolView";

    private MinischoolViewModule minischoolViewModule;

    public MinischoolManager(ReactApplicationContext reactApplicationContext) {
        minischoolViewModule = new MinischoolViewModule(reactApplicationContext);
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected MinischoolView createViewInstance(ThemedReactContext themedReactContext) {
        MinischoolView view = new MinischoolView(themedReactContext, minischoolViewModule.getActivity());

        Log.e("viewid", Integer.toString(view.getId()));

        return view;
    }

    @ReactProp(name = "url")
    public void setUrl(MinischoolView minischoolView, @Nullable String url) {
        minischoolView.web.loadUrl(url);
    }
}
