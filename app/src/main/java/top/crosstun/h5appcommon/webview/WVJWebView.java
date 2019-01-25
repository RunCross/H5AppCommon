package top.crosstun.h5appcommon.webview;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WVJWebView extends WebView {
    MyWebViewClient webViewClient;
    public WVJWebView(Context context) {
        super(context);
        init();
    }

    public WVJWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WVJWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

        WebSettings settings = getSettings();
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setSavePassword(false);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportMultipleWindows(true);
        settings.setUseWideViewPort(true); // 关键点
        settings.setAllowFileAccess(true); // 允许访问文件
        settings.setBuiltInZoomControls(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setLoadWithOverviewMode(true);
        settings.supportZoom();
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        webViewClient = new MyWebViewClient(this);


        webViewClient.registerHandler("app.post", new WVJBWebViewClient.WVJBHandler() {
            @Override
            public void request(Object data, WVJBWebViewClient.WVJBResponseCallback callback) {
                callback.callback("afdsvcrtyvt");
            }
        });

        setWebViewClient(webViewClient);
        setWebChromeClient(new WebChromeClient());


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    webViewClient.registerHandler("app.b", new WVJBWebViewClient.WVJBHandler() {
//                        @Override
//                        public void request(Object data, WVJBWebViewClient.WVJBResponseCallback callback) {
//                            callback.callback("dddddddddddddddd");
//                        }
//                    });
                    Thread.sleep(5*1000);
                    myhanlder.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Myhanlder myhanlder = new Myhanlder();

    class Myhanlder extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            webViewClient.callHandler("app.dd", "adsfjasdfasdf", new WVJBWebViewClient.WVJBResponseCallback() {
                @Override
                public void callback(Object data) {

                }
            });
        }
    }
}
