package com.pk.sai;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pk.sai.utils.AppConstants;
import com.pk.sai.utils.MyPreferences;

import sai.R;

public class DescriptionActivity extends AppCompatActivity {

    LinearLayout topLayout;
    private Bundle extras;
    WebView webView;
    int selectedChapter;
    boolean isChapterSelected=true;
    ImageButton forward, backward;
    TextView chapterTitle;
    String lang="";
    String url= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        extras = getIntent().getExtras();

        lang=MyPreferences.getStringPrefrences(AppConstants.APP_LANGUAGE,this,"en");

        if(lang.equals("en")){
            url=AppConstants.satcharitraUrlEnglish+"contents.html";
        }else{
            url=AppConstants.satcharitraUrlHindi+"contents.html";
        }

        if(!extras.equals(null)) {
            selectedChapter = extras.getInt("chapter");
            if(selectedChapter==101){
                isChapterSelected=false;
            }
        }

        if(isChapterSelected){
            init();
            operations();
        }else{
            topLayout= findViewById(R.id.topLayout);
            webView = (WebView)findViewById(R.id.content);
            topLayout.setVisibility(View.GONE);
            webView.loadUrl(url);
        }



    }

    @SuppressLint("SetTextI18n")
    private void operations() {

        if(lang.equals("en")){
            url=AppConstants.satcharitraUrlEnglish+ selectedChapter + ".html";
        }else{
            url=AppConstants.satcharitraUrlHindi+ selectedChapter + ".html";
        }

        switch (selectedChapter){

            case 16:
                chapterTitle.setText(getResources().getString(R.string.chapters)+" " +16 +" and "+17);
                break;
            case 18:
                chapterTitle.setText(getResources().getString(R.string.chapters)+" " +18 +" and "+19);
                break;
            case 43:
                chapterTitle.setText(getResources().getString(R.string.chapters)+" " +43 +" and "+44);
                break;
            case 51:
                chapterTitle.setText(getResources().getString(R.string.eplilogue));
                break;
            default:
                chapterTitle.setText(getResources().getString(R.string.chapter)+" "+selectedChapter);
                break;

        }
        webView.loadUrl(url);

        switch (selectedChapter){
            case 1:
                //make forward invisible
                backward.setVisibility(View.INVISIBLE);
                break;
            case 51:
                // Chapter 51 is Eplilogue.
                forward.setVisibility(View.INVISIBLE);
                //do something
                break;

            default:
                // do something
                forward.setVisibility(View.VISIBLE);
                backward.setVisibility(View.VISIBLE);
                break;

        }

    }

    private void init() {
        webView = (WebView)findViewById(R.id.content);
        forward = findViewById(R.id.forward);
        backward =findViewById(R.id.backward);
        chapterTitle = findViewById(R.id.chapterDetail);

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedChapter+= getChapterIncre();
                operations();
            }
        });

        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedChapter-= getChapterDecre();
                operations();
            }
        });
    }

    private int getChapterIncre() {
        if(selectedChapter==16 || selectedChapter==18 ||selectedChapter ==43){
            return 2;
        }else{
            return 1;
        }
    }

    private int getChapterDecre() {
        if(selectedChapter==18 || selectedChapter==20 ||selectedChapter ==45){
            return 2;
        }else{
            return 1;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}