package com.msdemorn;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import javax.annotation.Nullable;

class BrowserJSInterface {
    Context context;
    WebView webView;

    public BrowserJSInterface(Context context, WebView webView) {
        this.context = context;
        this.webView = webView;
    }

    @JavascriptInterface
    public void onStarted() {
        Toast.makeText(context, "onStarted", Toast.LENGTH_SHORT).show();

        WritableMap event = Arguments.createMap();
        event.putString("eName", "onStarted");
        if (this.context instanceof ReactContext) {
            Log.e("ERR", event.toString());

            ((ReactContext) this.context).getJSModule(RCTEventEmitter.class).receiveEvent(this.webView.getId(), "topChange", event);
        }
    }

    @JavascriptInterface
    public void onWait() {
        Toast.makeText(context, "onWait", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void onEnded() {
        Toast.makeText(context, "onEnded", Toast.LENGTH_SHORT).show();

        if (this.context instanceof Activity) {
            ((Activity) this.context).finish();
        }
    }

    public void onError(String code) {
        Toast.makeText(context, "onError: " + code, Toast.LENGTH_SHORT).show();

        if (this.context instanceof Activity) {
            ((Activity) this.context).finish();
        }
    }
}

public class MinischoolView extends FrameLayout {

    WebView web;

    public MinischoolView(Context context) {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.activity_browser, this, true);

        web = findViewById(R.id.web_main);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setAllowUniversalAccessFromFileURLs(true);
        web.getSettings().setLoadsImagesAutomatically(true);
        web.getSettings().setAllowContentAccess(true);
        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().setMediaPlaybackRequiresUserGesture(false);
        web.addJavascriptInterface(new BrowserJSInterface(context, web), "Minischool");
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

        //        web.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onPermissionRequest(final PermissionRequest request) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        request.grant(request.getResources());
//                    }
//                });
//            }
//        });
//
    }

    public MinischoolView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
