package com.msdemorn;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import javax.annotation.Nullable;

class BrowserJSInterface {
    Context mContext;
    WebView webView;
    int currentId;

    public BrowserJSInterface(Context context, WebView webView, int currentId) {
        this.mContext = context;
        this.webView = webView;
        this.currentId = currentId;
    }

    @JavascriptInterface
    public void onStarted() {
        WritableMap event = Arguments.createMap();
        event.putString("pathName", "onStarted");
        if (this.mContext instanceof ReactContext) {
            ((ReactContext) this.mContext).getJSModule(RCTEventEmitter.class).receiveEvent(this.currentId, "topChange", event);
        }
    }

    @JavascriptInterface
    public void onWait() {
        Toast.makeText(mContext, "onWait", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void onEnded() {
        WritableMap event = Arguments.createMap();
        event.putString("pathName", "onEnded");
        if (this.mContext instanceof ReactContext) {
            Log.e("onEnded", "onEnded");
            ((ReactContext) this.mContext).getJSModule(RCTEventEmitter.class).receiveEvent(this.currentId, "topChange", event);
        }

//        if (this.mContext instanceof Activity) {
//            ((Activity) this.mContext).finish();
//        }
    }

    public void onError(String code) {
        Toast.makeText(mContext, "onError: " + code, Toast.LENGTH_SHORT).show();

        if (this.mContext instanceof Activity) {
            ((Activity) this.mContext).finish();
        }
    }
}

public class MinischoolView extends LinearLayout {

    WebView web;
    Activity mActivity;
    Context mContext;

    String TAG = "Test123";

    public MinischoolView(Context context, Activity activity) {
        super(context);

        mActivity = activity;
        mContext = context;

        LayoutInflater.from(context).inflate(R.layout.activity_browser, this, true);

        web = findViewById(R.id.web_main);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setAllowUniversalAccessFromFileURLs(true);
        web.getSettings().setLoadsImagesAutomatically(true);
        web.getSettings().setAllowContentAccess(true);
        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().setMediaPlaybackRequiresUserGesture(false);

        web.getSettings().setAllowFileAccessFromFileURLs(true);
        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


        web.evaluateJavascript("window.isNative = true;", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                Log.d("LogName", s);
            }
        });
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
                web.setWebChromeClient(new WebChromeClient() {
                    // Grant permissions for cam
                    @Override
                    public void onPermissionRequest(final PermissionRequest request) {
                        Log.d(TAG, "onPermissionRequest");
                        activity.runOnUiThread(new Runnable() {
                            @TargetApi(Build.VERSION_CODES.M)
                            @Override
                            public void run() {
                                Log.d(TAG, request.getOrigin().toString());
                                request.grant(request.getResources());
//                                if(request.getOrigin().toString().equals("file:///")) {
//                                    Log.d(TAG, "GRANTED");
//                                    request.grant(request.getResources());
//                                } else {
//                                    Log.d(TAG, "DENIED");
//                                    request.deny();
//                                }
                            }
                        });
                    }
        });
//

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        web.addJavascriptInterface(new BrowserJSInterface(mContext, web, getId()), "Minischool");
    }
}
