package com.pk.sai;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pk.sai.adapters.MyResolveAdapter;
import com.pk.sai.adapters.resolveUDListener;
import com.pk.sai.models.MyResolvesModel;
import com.pk.sai.sqlite.DBHandler;
import com.pk.sai.utils.ResourceManager;

import java.util.ArrayList;
import java.util.Objects;

import sai.R;

public class MyResolveActivity extends AppCompatActivity implements resolveUDListener {

    private DBHandler dbHandler;
    private ArrayList<MyResolvesModel> mantraModalArrayList;
    LinearLayoutManager llm;
    MyResolveAdapter myResolveAdapter;
    RecyclerView myResolveRV;
    Button saveMyResolve;
    EditText myResolveET;
    ImageButton addResolveButton;


    Button cancel;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_resolves);

        ResourceManager.changeStatusBarColor(this,getResources().getColor(R.color.gold));

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar = findViewById(R.id.tool_de);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }


        myResolveRV = findViewById(R.id.my_resolves_rv);
        addResolveButton = findViewById(R.id.add_my_resolve);


        dbHandler = new DBHandler(MyResolveActivity.this);

        loadRecyclerElement();


        addResolveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_showMessage();
            }
        });


    }

    private void loadRecyclerElement() {
        mantraModalArrayList = dbHandler.readMantras();
        // on below line passing our array lost to our adapter class.
        myResolveAdapter= new MyResolveAdapter(mantraModalArrayList, MyResolveActivity.this);

        // setting layout manager for our recycler view.
        llm = new LinearLayoutManager(MyResolveActivity.this, RecyclerView.VERTICAL, false);
        myResolveRV.setLayoutManager(llm);

        // setting our adapter to recycler view.
        myResolveRV.setAdapter(myResolveAdapter);

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void btn_showMessage(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(MyResolveActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_add_resolve,null);
        saveMyResolve = mView.findViewById(R.id.my_resolve_save_);
        myResolveET = mView.findViewById(R.id.my_resolve_);
        cancel = mView.findViewById(R.id.cancel_add_resolve_dialog);



        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        saveMyResolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String myMantra = myResolveET.getText().toString();

                // validating if the text fields are empty or not.
                if (myMantra.isEmpty()) {
                    Toast.makeText(MyResolveActivity.this, getResources().getString(R.string.enter_resolve), Toast.LENGTH_SHORT).show();
                    return;
                }
                dbHandler.addNewResolve(myMantra);
                Toast.makeText(MyResolveActivity.this,  getResources().getString(R.string.resolve_added), Toast.LENGTH_SHORT).show();
                loadRecyclerElement();


                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }



    @Override
    public void resolveUDDone(ArrayList<MyResolvesModel> newResolveList, String message) {
        ResourceManager.displayShortToastMessage(this,message);

        myResolveAdapter= new MyResolveAdapter(newResolveList, MyResolveActivity.this);
        // setting our adapter to recycler view.
        myResolveRV.setAdapter(myResolveAdapter);
    }
}