package com.pk.sai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.pk.sai.adapters.ChapterAdapter;
import com.pk.sai.utils.GridSpacingItemDecoration;

import sai.R;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ChapterAdapter chapterAdapter;
    Button chapterList;
    ImageView saiBabaImage;

    // Chapter 51 is Epilogue
    int []chapters= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,18,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,45,46,47,48,49,50,51};
    int spanCount = 3; // 3 columns
    int spacing = 10; // 40px
    boolean includeEdge = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        saiBabaImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ImageDisplay.class));
            }
        });

        chapterList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
                intent.putExtra("chapter",101);
                startActivity(intent);

            }
        });

    }


    private void init() {
        saiBabaImage = findViewById(R.id.imageView);
        chapterList = findViewById(R.id.chapters);
        recyclerView = findViewById(R.id.chapterList);
        chapterAdapter = new ChapterAdapter(chapters,MainActivity.this);

        RecyclerView.LayoutManager manager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        recyclerView.setAdapter(chapterAdapter);
    }


}