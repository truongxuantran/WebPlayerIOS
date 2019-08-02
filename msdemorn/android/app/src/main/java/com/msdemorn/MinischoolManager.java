package com.msdemorn;

import android.util.Log;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import javax.annotation.Nullable;

public class MinischoolManager extends SimpleViewManager<MinischoolView> {
    public static final String REACT_CLASS = "MinischoolView";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected MinischoolView createViewInstance(ThemedReactContext themedReactContext) {
        return new MinischoolView(themedReactContext);
    }

    @ReactProp(name = "url")
    public void setUrl(MinischoolView minischoolView, @Nullable String url) {
        minischoolView.web.loadUrl(url);
    }
}
