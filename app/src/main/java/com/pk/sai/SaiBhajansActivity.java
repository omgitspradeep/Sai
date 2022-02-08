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

    RecyclerView saiAartiListRV, saiKritiListRV;
    LinearLayoutManager linearLayoutManager,linearLayoutManager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sai_bhajans);

        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.sai_kriti_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        saiAartiListRV = findViewById(R.id.sai_aarti_rv);
        saiKritiListRV = findViewById(R.id.sai_kriti_rv);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // Sai Aarti
        SaiBhajansAdapter saiAartiAdapter = new SaiBhajansAdapter(this, ResourceManager.getAartiModelArrayList());
        saiAartiListRV.setLayoutManager(linearLayoutManager);
        saiAartiListRV.setAdapter(saiAartiAdapter);

        // Sai Kriti
        SaiBhajansAdapter saiKritiAdapter = new SaiBhajansAdapter(this, ResourceManager.getSaiKritiModelArrayList());
        saiKritiListRV.setLayoutManager(linearLayoutManager2);
        saiKritiListRV.setAdapter(saiKritiAdapter);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}