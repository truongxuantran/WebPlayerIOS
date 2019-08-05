//package com.msdemorn;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import android.util.Log;
//import android.view.View;
//import android.webkit.JavascriptInterface;
//import android.webkit.PermissionRequest;
//import android.webkit.ValueCallback;
//import android.webkit.WebChromeClient;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.Toast;
//
//import com.facebook.react.BuildConfig;
//import com.facebook.react.ReactActivity;
//import com.facebook.react.ReactInstanceManager;
//import com.facebook.react.ReactRootView;
//import com.facebook.react.common.LifecycleState;
//import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
//import com.facebook.react.shell.MainReactPackage;
//
//public class BrowserActivity extends ReactActivity {
//    private ReactRootView reactRootView;
//    private ReactInstanceManager reactInstanceManager;
//
//    public static final String MSP3 = "msp3://";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_browser);
//
//        WebView web = findViewById(R.id.web_main);
//        web.getSettings().setJavaScriptEnabled(true);
//        web.getSettings().setAllowUniversalAccessFromFileURLs(true);
//        web.getSettings().setLoadsImagesAutomatically(true);
//        web.getSettings().setAllowContentAccess(true);
//        web.getSettings().setDomStorageEnabled(true);
//        web.getSettings().setMediaPlaybackRequiresUserGesture(false);
//        web.addJavascriptInterface(new BrowserJSInterface(this, web), "Minischool");
//        web.evaluateJavascript("window.isNative = true;", new ValueCallback<String>() {
//            @Override
//            public void onReceiveValue(String s) {
//                Log.d("LogName", s);
//            }
//        });
//
//        web.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return false;
//            }
//        });
//
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
//        Intent in = getIntent();
//        String url = in.getExtras().getString("url");
//        Uri uri = in.getData();
//        if (uri != null) {
//            url = uri.toString();
//            // Replace url scheme.
//            url = url.replaceAll(this.MSP3, "https://");
//        }
//
//        // Load url.
//        web.loadUrl(url);
//
//        Toast.makeText(this, url.toString(), Toast.LENGTH_SHORT).show();
//    }
//
//}
