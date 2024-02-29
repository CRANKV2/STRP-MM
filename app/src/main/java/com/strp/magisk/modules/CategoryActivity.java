package com.strp.magisk.modules;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class CategoryActivity extends AppCompatActivity {

        BottomNavigationView bottomnavigation;

        WebView webview;
        LottieAnimationView loading;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            getWindow().requestFeature(Window.FEATURE_PROGRESS);
            setContentView(R.layout.activity_category);

            webview = findViewById(R.id.category_webview);
            loading = findViewById(R.id.loading);

            webview.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress)
                {
                    //Make the bar disappear after URL is loaded, and changes string to Loading...
                    //setTitle("Loading...");
                    setProgress(progress * 100); //Make the bar disappear after URL is loaded

                    // Return the app name after finish loading
                    if(progress == 100){
                        loading.setVisibility(View.INVISIBLE);
                    }else {
                        loading.setVisibility(View.VISIBLE);
                    }

                }
            });
            webview.setWebViewClient(new HelloWebViewClient());
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl("https://strp.cloud/dash/");

            bottomnavigation = findViewById(R.id.category_navigation);
            bottomnavigation.setSelectedItemId(R.id.category);

            bottomnavigation.setOnItemSelectedListener(menuItem -> {
                if (menuItem.getItemId() == R.id.home) {
                    // Starten der Home-Activity
                    startActivity(new Intent(getApplication(), HomeActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (menuItem.getItemId() == R.id.login) {
                    // Starten der Login-Activity
                    startActivity(new Intent(getApplication(), LoginActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (menuItem.getItemId() == R.id.category) {
                    // Starten der Category-Activity
                    startActivity(new Intent(getApplication(), CategoryActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (menuItem.getItemId() == R.id.cart) {
                    // Starten der Cart-Activity
                    startActivity(new Intent(getApplication(), CartActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }

                // Standardverhalten
                return false;
            });
        }


    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Uri uri = request.getUrl();

            // Log the URL being inspected
            Log.d("WebViewLink", "Inspecting URL: " + uri.toString());

            // Internal Links: Stay within WebView
            if (uri.getHost() != null && uri.getHost().startsWith("https://strp.cloud")) {
                Log.d("WebViewLink", "Internal Match: Loading in WebView");
                return false;
            } else {
                // External Links: Open in Browser
                Log.d("WebViewLink", "External: Opening in Browser");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;
            }
        }
    }

}