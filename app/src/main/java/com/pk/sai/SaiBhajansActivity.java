package com.pk.sai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.pk.sai.adapters.SaiBhajansAdapter;
import com.pk.sai.utils.ResourceManager;

import java.util.Objects;

import sai.R;

public class SaiBhajansActivity extends AppCompatActivity {

    RecyclerView impMantraListRV;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sai_bhajans);

        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.sai_bhajan));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        impMantraListRV = findViewById(R.id.imp_stuff_rv);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        SaiBhajansAdapter importantStuffsAdapter = new SaiBhajansAdapter(this, ResourceManager.getBhajanModelArrayList());
        impMantraListRV.setLayoutManager(linearLayoutManager);
        impMantraListRV.setAdapter(importantStuffsAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}