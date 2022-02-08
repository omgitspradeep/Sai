package com.pk.sai;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.pk.sai.models.SaiNamesModel;
import com.pk.sai.utils.CircularSeekBar;
import com.pk.sai.utils.ResourceManager;

import java.util.ArrayList;
import java.util.List;

import sai.R;

public class SaiNamesActivity extends AppCompatActivity {
    TextView desc,nameTV, jumpStarting;
    EditText jumpDestination;
    boolean showDetail=true;
    CircularSeekBar seekBar;
    Button next, jumpButton;
    ImageButton previous, mainJump, localLanguageSelector;
    LinearLayout languageLayout;
    RelativeLayout parentLayout;
    int min = 1;
    int current = 1;
    int max=108;
    private List<SaiNamesModel> viewItems = new ArrayList<>();
    int langCode=2;  // lang 1: Bengali , 2: English, 3: Gujrati, 4: Hindi, 5: Malyalam, 6: Marathi, 7: Nepali, 8: Oriya, 9: Punjabi, 10: Tamil, 11: Telgu

    private static long back_pressed;
    private static final int TIME_DELAY = 2000;
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sai_names);
        viewItems= ResourceManager.getSaiNamesModelArrayList();
        // To make top status bar disappear
        ResourceManager.changeStatusBarColor(this,getResources().getColor(R.color.white));
        init();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(current<max){
                    updateOnNext();
                }else{
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SaiNamesActivity.this);
                    alertDialogBuilder.setMessage("You have completed your chant.\nChant again?");
                    alertDialogBuilder.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    current=1;
                                    setChanges();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current>min){
                    updateOnPrevious();
                }else{
                    Toast.makeText(SaiNamesActivity.this, "Sorry, You are at start.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mainJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpDialog();
            }
        });

        localLanguageSelector.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onClick(View v) {
                languageLayout.setVisibility(View.VISIBLE);
                parentLayout.setVisibility(View.INVISIBLE);
            }
        });
    }



    private void init() {
        nameTV = findViewById(R.id.k_names_names);
        desc = findViewById(R.id.k_knames_desc);
        next = findViewById(R.id.next_button_click_knames);
        previous = findViewById(R.id.chanting_prev_button_click_knames);
        seekBar = findViewById(R.id.circularSeekBar_knames);
        mainJump = findViewById(R.id.jump_main_screen);
        localLanguageSelector = findViewById(R.id.language_selction_main);
        languageLayout = findViewById(R.id.language_layout_main);
        parentLayout = findViewById(R.id.parent_layout_);

        // SeekBar Initial setup
        seekBar.setMax(max);
        seekBar.setProgress(current);
        //  seekbar.setOnSeekBarChangeListener(new CircleSeekBarListener());

        // Initial display Setup
        next.setText(current+"");
        displayChanges();
        // check Selected radio button
        checkSelectedRadioButton();

    }

    private void checkSelectedRadioButton() {
        RadioButton radioButton;
        switch (langCode){
            case 1:
                radioButton = findViewById(R.id.selector_lang_bengali);
                radioButton.setChecked(true);
                break;
            case 2:
                radioButton = findViewById(R.id.selector_lang_english);
                radioButton.setChecked(true);
                break;
            case 3:
                radioButton = findViewById(R.id.selector_lang_gujrati);
                radioButton.setChecked(true);
                break;
            case 4:
                radioButton = findViewById(R.id.selector_lang_hindi);
                radioButton.setChecked(true);
                break;
            case 5:
                radioButton = findViewById(R.id.selector_lang_malyalam);
                radioButton.setChecked(true);
                break;
            case 6:
                radioButton = findViewById(R.id.selector_lang_marathi);
                radioButton.setChecked(true);
                break;
            case 7:
                radioButton = findViewById(R.id.selector_lang_nepali);
                radioButton.setChecked(true);
                break;
            case 8:
                radioButton = findViewById(R.id.selector_lang_oriya);
                radioButton.setChecked(true);
                break;
            case 9:
                radioButton = findViewById(R.id.selector_lang_punjabi);
                radioButton.setChecked(true);
                break;
            case 10:
                radioButton = findViewById(R.id.selector_lang_tamil);
                radioButton.setChecked(true);
                break;
            case 11:
                radioButton = findViewById(R.id.selector_lang_telgu);
                radioButton.setChecked(true);
                break;

        }
    }

    public void updateOnNext() {
        current+=1;
        setChanges();

    }

    private void setChanges() {
        seekBar.setProgress(current);
        next.setText(current+"");
        displayChanges();
    }

    private void displayChanges() {
        switch(langCode){
            case 1:
                nameTV.setText(viewItems.get(current-1).getName_bengali());
                desc.setText(viewItems.get(current-1).getD_bengali());
                break;
            case 2:
                nameTV.setText(viewItems.get(current-1).getName_english());
                desc.setText(viewItems.get(current-1).getD_eng());
                break;
            case 3:
                nameTV.setText(viewItems.get(current-1).getName_gujrati());
                desc.setText(viewItems.get(current-1).getD_gujrati());
                break;
            case 4:
                nameTV.setText(viewItems.get(current-1).getName_devnagari());
                desc.setText(viewItems.get(current-1).getD_hindi());
                break;
            case 5:
                nameTV.setText(viewItems.get(current-1).getName_malyalam());
                desc.setText(viewItems.get(current-1).getD_malyalam());
                break;
            case 6:
                nameTV.setText(viewItems.get(current-1).getName_devnagari());
                desc.setText(viewItems.get(current-1).getD_marathi());
                break;
            case 7:
                nameTV.setText(viewItems.get(current-1).getName_devnagari());
                desc.setText(viewItems.get(current-1).getD_nepali());
                break;
            case 8:
                nameTV.setText(viewItems.get(current-1).getName_oriya());
                desc.setText(viewItems.get(current-1).getD_oriya());
                break;
            case 9:
                nameTV.setText(viewItems.get(current-1).getName_punjabi());
                desc.setText(viewItems.get(current-1).getD_punjabi());
                break;
            case 10:
                nameTV.setText(viewItems.get(current-1).getName_tamil());
                desc.setText(viewItems.get(current-1).getD_tamil());
                break;
            case 11:
                nameTV.setText(viewItems.get(current-1).getName_telgu());
                desc.setText(viewItems.get(current-1).getD_telgu());
                break;
        }


    }

    public void updateOnPrevious() {
        current-=1;
        setChanges();
    }

    public void hideDesc(View view) {
        if(showDetail){
            desc.setVisibility(View.INVISIBLE);
            mainJump.setVisibility(View.GONE);
            localLanguageSelector.setVisibility(View.GONE);
        }else{
            desc.setVisibility(View.VISIBLE);
            mainJump.setVisibility(View.VISIBLE);
            localLanguageSelector.setVisibility(View.VISIBLE);

        }
        showDetail=!showDetail;

    }

    public void hideLanguageSelection(View view) {
        languageLayout.setVisibility(View.GONE);
        parentLayout.setVisibility(View.VISIBLE);

    }

    public class CircleSeekBarListener implements CircularSeekBar.OnCircularSeekBarChangeListener {
        @Override
        public void onProgressChanged(CircularSeekBar circularSeekBar, int progress, boolean fromUser) {
            // TODO Insert your code here

        }

        @Override
        public void onStopTrackingTouch(CircularSeekBar seekBar) {

        }

        @Override
        public void onStartTrackingTouch(CircularSeekBar seekBar) {

        }
    }


    public void jumpDialog(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(SaiNamesActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_jump_mantra,null);
        jumpStarting = mView.findViewById(R.id.jump_start_point);

        jumpDestination = mView.findViewById(R.id.jump_stop_point);
        jumpButton = mView.findViewById(R.id.dialog_jump);

        jumpStarting.setText(current+"");

        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        //alertDialog.setCanceledOnTouchOutside(false);

        jumpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String numberToJump = jumpDestination.getText().toString();

                // validating if the text fields are empty or not.
                if (!numberToJump.isEmpty()) {
                    int num = Integer.parseInt(numberToJump);
                    if(num<=max && num > 0){
                        setJumpFunctionality(num);
                    }else{
                        Toast.makeText(SaiNamesActivity.this, "Please Enter a number between 1 and "+max, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else{
                    Toast.makeText(SaiNamesActivity.this, "Please Enter a number between 1 and "+max, Toast.LENGTH_SHORT).show();
                    return;
                }


                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void setJumpFunctionality(int num) {
        // Change only if selected destination is not current one.
        if (num!=current){
            current=num;
            setChanges();
        }
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }


    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.selector_lang_bengali:
                if(checked){
                    langCode=1;
                    nameTV.setText(viewItems.get(current-1).getName_bengali());
                    desc.setText(viewItems.get(current-1).getD_bengali());
                }
                break;

            case R.id.selector_lang_english:
                if(checked){
                    langCode=2;
                    nameTV.setText(viewItems.get(current-1).getName_english());
                    desc.setText(viewItems.get(current-1).getD_eng());
                }
                break;


            case R.id.selector_lang_gujrati:
                if(checked){
                    langCode=3;
                    nameTV.setText(viewItems.get(current-1).getName_gujrati());
                    desc.setText(viewItems.get(current-1).getD_gujrati());
                }
                break;

            case R.id.selector_lang_hindi:
                if(checked){
                    langCode=4;
                    nameTV.setText(viewItems.get(current-1).getName_devnagari());
                    desc.setText(viewItems.get(current-1).getD_hindi());
                }
                break;
            case R.id.selector_lang_malyalam:
                if(checked){
                    langCode=5;
                    nameTV.setText(viewItems.get(current-1).getName_malyalam());
                    desc.setText(viewItems.get(current-1).getD_malyalam());
                }
                break;
            case R.id.selector_lang_marathi:
                if(checked){
                    langCode=6;
                    nameTV.setText(viewItems.get(current-1).getName_devnagari());
                    desc.setText(viewItems.get(current-1).getD_marathi());
                }
                break;
            case R.id.selector_lang_nepali:
                if(checked){
                    langCode=7;
                    nameTV.setText(viewItems.get(current-1).getName_devnagari());
                    desc.setText(viewItems.get(current-1).getD_nepali());
                }
                break;
            case R.id.selector_lang_oriya:
                if(checked){
                    langCode=8;
                    nameTV.setText(viewItems.get(current-1).getName_oriya());
                    desc.setText(viewItems.get(current-1).getD_oriya());
                }
                break;
            case R.id.selector_lang_punjabi:
                if(checked){
                    langCode=9;
                    nameTV.setText(viewItems.get(current-1).getName_punjabi());
                    desc.setText(viewItems.get(current-1).getD_punjabi());
                }
                break;
            case R.id.selector_lang_tamil:
                if(checked){
                    langCode=10;
                    nameTV.setText(viewItems.get(current-1).getName_tamil());
                    desc.setText(viewItems.get(current-1).getD_tamil());
                }
                break;

            case R.id.selector_lang_telgu:
                if(checked){
                    langCode=11;
                    nameTV.setText(viewItems.get(current-1).getName_telgu());
                    desc.setText(viewItems.get(current-1).getD_telgu());
                }
                break;


            }
        }



}