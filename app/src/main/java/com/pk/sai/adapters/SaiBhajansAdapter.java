package com.pk.sai.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pk.sai.SaiBhajansActivity;
import com.pk.sai.UrlReadActivity;
import com.pk.sai.models.BhajanModel;

import java.util.ArrayList;

import sai.R;

public class SaiBhajansAdapter extends RecyclerView.Adapter<SaiBhajansAdapter.Viewholder>{

    Context context;
    ArrayList<BhajanModel> listOfBhajans;

    public SaiBhajansAdapter(SaiBhajansActivity saiBhajansActivity, ArrayList<BhajanModel> bhajanModelArrayList) {
        this.listOfBhajans=bhajanModelArrayList;
        this.context = saiBhajansActivity;
    }

    @NonNull
    @Override
    public SaiBhajansAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_important_bhajan, parent, false);
        return new SaiBhajansAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaiBhajansAdapter.Viewholder holder, int position) {
        BhajanModel bhn = listOfBhajans.get(position);
        holder.sn.setText((position+1)+".");
        holder.aartiTitle.setText(bhn.getBhajanTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UrlReadActivity.class);
                i.putExtra("title_",bhn.getBhajanTitle());
                i.putExtra("url_",bhn.getBhajanUrl());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfBhajans.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView aartiTitle, sn;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            aartiTitle= itemView.findViewById(R.id.imp_stuff_title);
            sn= itemView.findViewById(R.id.imp_stuff_title_sn);
        }
    }
}
