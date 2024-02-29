package com.strp.magisk.modules;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.strp.magisk.modules.R;
import java.util.List;
import java.util.Arrays;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {


        BottomNavigationView bottomnavigation;

        WebView webview;
        LottieAnimationView loading;

        private WebView mywebView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().requestFeature(Window.FEATURE_PROGRESS);
            setContentView(R.layout.activity_login);

            webview = findViewById(R.id.login_webview);
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
            webview.loadUrl("https://strp.cloud/strp/links.html");

            bottomnavigation = findViewById(R.id.login_navigation);
            bottomnavigation.setSelectedItemId(R.id.login);

            bottomnavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
                }
            });
        }


    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Uri uri = request.getUrl();

            // Check if the host belongs to your website's domain
            if (Objects.equals(uri.getHost(), "https://strp.cloud/strp/links.html")) {
                return false; // Load links within your website in the WebView
            } else {
                // Handle external links with an Intent
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;
            }
        }
    }
}