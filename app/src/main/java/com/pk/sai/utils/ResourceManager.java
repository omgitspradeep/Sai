package com.pk.sai.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pk.sai.MyResolveActivity;
import com.pk.sai.models.BhajanModel;
import com.pk.sai.models.SaiNamesModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sai.R;

public class ResourceManager {
    static ArrayList<BhajanModel> bhajanModelArrayList;
    static ArrayList<SaiNamesModel> saiNamesModelArrayList;
    public static Locale locale;
    public static Resources res;


    public static void changeStatusBarColor(AppCompatActivity mContext, int color){
        Window window = mContext.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(color);
    }
    //getResources().getString()
    public static void createBhajanModelArrayList(Context mContext){
        bhajanModelArrayList= new ArrayList<>();
        bhajanModelArrayList.add(new BhajanModel(mContext.getResources().getString(R.string.bhupali_aarti),AppConstants.bhupali));
        bhajanModelArrayList.add(new BhajanModel(mContext.getResources().getString(R.string.kakad_aarti),AppConstants.kakad));
        bhajanModelArrayList.add(new BhajanModel(mContext.getResources().getString(R.string.madhyan_aarti),AppConstants.madhyan));
        bhajanModelArrayList.add(new BhajanModel(mContext.getResources().getString(R.string.dhup_aarti),AppConstants.dhup));
        bhajanModelArrayList.add(new BhajanModel(mContext.getResources().getString(R.string.shej_aarti),AppConstants.shej));
    }


    public static ArrayList<BhajanModel> getBhajanModelArrayList() {
        return bhajanModelArrayList;
    }

    public static void displayShortToastMessage(MyResolveActivity myResolveActivity, String message) {
        Toast.makeText(myResolveActivity, message, Toast.LENGTH_SHORT).show();
    }

    public static List<SaiNamesModel> getSaiNamesModelArrayList() {
        return saiNamesModelArrayList;
    }

    public static void setKnamesModelArrayList(ArrayList<SaiNamesModel> knamesModelArrayList) {
        ResourceManager.saiNamesModelArrayList = knamesModelArrayList;
    }

    public static void setLocale(Context mContext,String localLang){
        locale = new Locale(localLang);
        res = mContext.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
    }
}
