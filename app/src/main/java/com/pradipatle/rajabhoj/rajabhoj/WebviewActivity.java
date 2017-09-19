package com.pradipatle.rajabhoj.rajabhoj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by Aeon-02 on 9/1/2017.
 */

public class WebviewActivity extends Activity {
    private WebView WebViewMediaReview;
    String URLcode;
    private ProgressBar progressbar = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);

        progressbar = (ProgressBar)findViewById(R.id.progressBar11);
        progressbar.setMax(100);
        //Retrieve the value
        URLcode = "https://en.wikipedia.org/wiki/Bhoja";
        progressbar.setVisibility(View.VISIBLE);
        WebViewMediaReview =(WebView)findViewById(R.id.webViewGallery);
        WebViewMediaReview.getSettings().setLoadsImagesAutomatically(true);
        WebViewMediaReview.getSettings().setJavaScriptEnabled(true);
        WebViewMediaReview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebViewMediaReview.loadUrl(URLcode);
        WebViewMediaReview.setWebViewClient(new WebViewClient());
        WebViewMediaReview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                progressbar.setVisibility(View.VISIBLE);
                progressbar.setProgress(progress);
                if (progress == 100) {
                    progressbar.setVisibility(View.GONE); // Make the bar disappear after URL is loaded
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}


