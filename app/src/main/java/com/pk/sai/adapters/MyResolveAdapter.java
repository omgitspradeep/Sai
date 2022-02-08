package com.pk.sai.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pk.sai.MyResolveActivity;
import com.pk.sai.models.MyResolvesModel;
import com.pk.sai.sqlite.DBHandler;

import java.util.ArrayList;

import sai.R;

public class MyResolveAdapter extends RecyclerView.Adapter<MyResolveAdapter.ViewHolder>{
    private ArrayList<MyResolvesModel> resolvesModelArrayList;
    private MyResolveActivity mContext;

    public MyResolveAdapter(ArrayList<MyResolvesModel> resolvesModelArrayList, MyResolveActivity context) {
        this.resolvesModelArrayList=resolvesModelArrayList;
        this.mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_my_resolves, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int tempVal=position;
        MyResolvesModel modal = resolvesModelArrayList.get(tempVal);
        String resolveText= modal.getMyPromise();

        holder.myResolveTV.setText(modal.getMyPromise());
        holder.myResolveUpdateBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                View mView = mContext.getLayoutInflater().inflate(R.layout.dialog_add_resolve,null);
                Button updateMyResolve = mView.findViewById(R.id.my_resolve_save_);
                EditText myResolveET = mView.findViewById(R.id.my_resolve_);
                Button cancel = mView.findViewById(R.id.cancel_add_resolve_dialog);

                myResolveET.setText(modal.getMyPromise());
                updateMyResolve.setText(R.string.update);

                alert.setView(mView);
                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                updateMyResolve.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String myResolve = myResolveET.getText().toString();


                        // validating if the text fields are empty or not.
                        if (myResolve.isEmpty()) {
                            Toast.makeText(mContext, mContext.getResources().getString(R.string.enter_resolve), Toast.LENGTH_SHORT).show();
                            return;
                        }


                        boolean flag= new DBHandler(mContext).onUpdateResolve(new MyResolvesModel(modal.getId(),myResolve));
                        if(flag){
                            resolvesModelArrayList.get(tempVal).setMyPromise(myResolve);
                            mContext.resolveUDDone(resolvesModelArrayList, mContext.getResources().getString(R.string.updated_resolve));
                        }

                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();

            }
        });

        holder.myResolveDeleteBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                alertDialogBuilder.setMessage(R.string.delete_forever);
                alertDialogBuilder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                // Delete the selected Resolve from database
                                boolean b=new DBHandler(mContext).deleteSingleRow(modal.getId());
                                if(b){
                                    resolvesModelArrayList.remove(tempVal);
                                    mContext.resolveUDDone(resolvesModelArrayList, mContext.getResources().getString(R.string.deleted_resolve));
                                }

                            }
                        });

                alertDialogBuilder.setNegativeButton(R.string.no,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return resolvesModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView myResolveTV;
        private ImageButton myResolveUpdateBut, myResolveDeleteBut;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            myResolveTV = itemView.findViewById(R.id.adap_my_resolve_);
            myResolveDeleteBut = itemView.findViewById(R.id.adap_my_resolve_delete);
            myResolveUpdateBut = itemView.findViewById(R.id.adap_my_resolve_update);

        }
    }

}
