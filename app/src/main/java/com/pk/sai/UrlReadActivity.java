package com.pk.sai;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.pk.sai.utils.ResourceManager;

import sai.R;

public class UrlReadActivity extends AppCompatActivity{


    WebView webView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_read);
        Intent i=getIntent();
        String title =i.getStringExtra("title_");
        String url =i.getStringExtra("url_");

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = findViewById(R.id.poen_wv);
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int tl=ResourceManager.getTextLabel();
                float fontSize;
                switch(tl){
                    case 0:
                        tl=tl+1;
                        fontSize = getResources().getDimension(R.dimen.textAppearance_mdpi_06_sp);
                        updateTextSize(fontSize);
                        break;
                    case 1:
                        tl=tl+1;
                        fontSize = getResources().getDimension(R.dimen.textAppearance_mdpi_08_sp);
                        updateTextSize(fontSize);
                        break;
                    case 2:
                        tl=0;
                        fontSize = 15f*getResources().getConfiguration().fontScale;
                        updateTextSize(fontSize);
                        break;
                }
                ResourceManager.setTextLabel(tl);
                return false;
            }
        });


    }

    private void updateTextSize(float fontSize) {
        WebSettings webSettings =  webView.getSettings();
        webSettings.setDefaultFontSize((int)fontSize);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
