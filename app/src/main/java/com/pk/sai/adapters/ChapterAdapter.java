package com.pk.sai.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pk.sai.DescriptionActivity;

import sai.R;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.MyViewHolder> {

    int[] bookDataList;
    Context mContext;

    public ChapterAdapter(int[] bookDataList,Context context) {
        this.bookDataList = bookDataList;
        mContext =context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int chapterId= bookDataList[position];
        if(chapterId==51){
            holder.chID.setText(R.string.eplilogue);
        }else{
            holder.chID.setText(chapterId+"");
        }
        holder.chID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DescriptionActivity.class);
                intent.putExtra("chapter",chapterId);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookDataList.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView chID;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            chID = itemView.findViewById(R.id.chapterName);
        }
    }
}
