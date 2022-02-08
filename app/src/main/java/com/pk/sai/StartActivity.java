package com.pk.sai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pk.sai.models.SaiNamesModel;
import com.pk.sai.utils.AppConstants;
import com.pk.sai.utils.MyPreferences;
import com.pk.sai.utils.ResourceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;

import sai.BuildConfig;
import sai.R;

public class StartActivity extends AppCompatActivity {

    AppCompatButton saiSatcharitra, saiNames, saiBhajans, saiAssurances, myResolve;


    FloatingActionButton saiSettings;
    Button settingOK, shareAppButton;
    int mainLangCode=0;  // 0: english , 1: hindi , 2:nepali
    String languageToLoad="",currentLanguage="";
    Locale locale;
    boolean isLanguageSelectd=false;
    RadioButton tempRadioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        currentLanguage = MyPreferences.getStringPrefrences(AppConstants.APP_LANGUAGE,this,"en");
        init();

        // To make top status bar disappear
        ResourceManager.changeStatusBarColor(this,getResources().getColor(R.color.white));

        saiNames.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(StartActivity.this, SaiNamesActivity.class));
                }
            });

        saiSatcharitra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(StartActivity.this, MainActivity.class));
                }
            });

        saiAssurances.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent in = new Intent(StartActivity.this, UrlReadActivity.class);
                    in.putExtra("title_",getResources().getString(R.string.sai_saying));
                    in.putExtra("url_", getAssuranceUrl());
                    startActivity(in);
                }
            });

        saiBhajans.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(StartActivity.this, SaiBhajansActivity.class));
                }
            });

        saiSettings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Open a popup window for language settings
                    showSetting();

                }
            });

        myResolve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Open a popup window for language settings
                    startActivity(new Intent(StartActivity.this, MyResolveActivity.class));
                }
            });

    }

    private String getAssuranceUrl() {
        if(currentLanguage.equals("en")){
            return AppConstants.saiSayingsEnglish;
        }else if(currentLanguage.equals("hi")){
            return AppConstants.saiSayingsHindi;
        }else{
            return AppConstants.saiSayingsNepali;
        }
    }


    private void init() {
        saiSatcharitra = findViewById(R.id.sai_satcharitra);
        saiNames = findViewById(R.id.names_of_sai_baba);
        saiBhajans = findViewById(R.id.sai_bhajans);
        saiAssurances = findViewById(R.id.sai_assurances);
        saiSettings = findViewById(R.id.app_settings);
        myResolve = findViewById(R.id.my_resolve_start);

    }


    public void showSetting(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(StartActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.setting_layout,null);
     //   saveMyResolve = mView.findViewById(R.id.my_resolve_save_);
        settingOK = mView.findViewById(R.id.setting_button_ok);
        shareAppButton = mView.findViewById(R.id.share_app_link);

        shareAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareApp();
            }
        });
        setCheckerOnLanguage(mView);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        settingOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLanguageSettingOKButtonPressed();
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void setCheckerOnLanguage(View mView) {
        if(currentLanguage.equals("hi")){
            tempRadioButton = mView.findViewById(R.id.main_lang_selection_hindi);
            tempRadioButton.setChecked(true);
        }else{
            tempRadioButton = mView.findViewById(R.id.main_lang_selection_english);
            tempRadioButton.setChecked(true);
        }
    }


    @SuppressLint("NonConstantResourceId")
    public void onLanguageButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.main_lang_selection_english:
                if(checked){
                    isLanguageSelectd=true;
                    languageToLoad= "en";
                }
                break;
            case R.id.main_lang_selection_hindi:
                if(checked){
                    isLanguageSelectd=true;
                    languageToLoad= "hi";
                }
                break;
        }

    }

    private void onLanguageSettingOKButtonPressed() {
        if(isLanguageSelectd){
            setLocale(languageToLoad);
        }
    }

    private void setLocale(String localeName) {
        if (!localeName.equals(currentLanguage)) {
            ResourceManager.setLocale(this,localeName);
            Intent refresh = new Intent(this, StartActivity.class);
            startActivity(refresh);
            MyPreferences.setStringPrefrences(AppConstants.APP_LANGUAGE,localeName,StartActivity.this);
        } else {
            Toast.makeText(this, getResources().getString(R.string.lang_already_selected), Toast.LENGTH_SHORT).show();
        }
        isLanguageSelectd=false;
    }


    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }

    private void shareApp(){
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Sai");
            String shareMessage= "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch(Exception e) {
            //e.toString();
        }
    }

}