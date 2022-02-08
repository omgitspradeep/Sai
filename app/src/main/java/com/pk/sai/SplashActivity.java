package com.pk.sai;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

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

import sai.R;

public class SplashActivity extends AppCompatActivity {

    String localLang="en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localLang = MyPreferences.getStringPrefrences(AppConstants.APP_LANGUAGE,this,"en");
        if(!localLang.equalsIgnoreCase("en")){
            localLang="hi";
            ResourceManager.setLocale(this,localLang);
        }

        others();
        Intent landingPage = new Intent(this, StartActivity.class);
        startActivity(landingPage);

    }

    private void others() {
        ResourceManager.createBhajanModelArrayList(this);
        try {
            listSainames();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listSainames() throws IOException {

        InputStream is = getResources().openRawResource(R.raw.sai_names);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        String line = "";
        ArrayList<SaiNamesModel> outList = new ArrayList<>();

        try {
            while ((line = reader.readLine()) != null) {
                // Split the line into different tokens (using the comma as a separator).

                String[] mantraTokens = line.split(",");

                // Read the data and store it in the WellData POJO.

                outList.add(new SaiNamesModel(Integer.parseInt(mantraTokens[0]),mantraTokens[1],mantraTokens[2],mantraTokens[3],mantraTokens[4],mantraTokens[5],mantraTokens[6],mantraTokens[7],mantraTokens[8],mantraTokens[9],mantraTokens[10],mantraTokens[11],mantraTokens[12],mantraTokens[13],mantraTokens[14],mantraTokens[15],mantraTokens[16],mantraTokens[17],mantraTokens[18],mantraTokens[19],mantraTokens[20],mantraTokens[21],mantraTokens[22]));
            }
            Log.d("MainActivity" ,"Just Created " );

        } catch (IOException e1) {
            Log.e("MainActivity", "Error" + line, e1);
            e1.printStackTrace();
        }


        //


        ResourceManager.setKnamesModelArrayList(outList);
    }

}