package com.sudotechpng.android.evdreselleraid;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    TextView number;
    TextView amount;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.frame);
//        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();
        String[] mString = getResources().getStringArray(R.array.options);
        for (int i=0; i<mString.length; i++){
            input.add(mString[i]);
        }
        mAdapter = new MyAdapter(input);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

//        Button sellEVDButton = (Button) findViewById(R.id.sendTopapDialler);
//        sellEVDButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,"Sending Topap",Toast.LENGTH_SHORT).show();
//                dialServiceTopap(v);
//            }
//        });
    }

//    public void setFragment(Fragment f) {
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
//        ft.replace(R.id.frame, f);
//        ft.commit();
//    }

    public int getResellerPIN() {
        SharedPreferences mSharedPref = getSharedPreferences("ResellerPIN", MODE_PRIVATE);
        return mSharedPref.getInt("evdpin", 8464);
    }

    public void dialServiceTopap(View view) {
        number = findViewById(R.id.topap_phone_number);
        amount = findViewById(R.id.topap_ammount);
        String topapNumber = String.valueOf(number.getText());
        String topapAmmount = String.valueOf(amount.getText());
        String dialCode = String.format("*889*2*%s*%s*%s#", topapNumber, topapAmmount, getResellerPIN());
        try {
            dialCode = URLEncoder.encode(dialCode,"UTF-8");
            Intent dial = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dialCode));
//            if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
            startActivity(dial);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void dialServiceEasypay(View view) {
        number = findViewById(R.id.easypay_phone_number);
        amount = findViewById(R.id.easypay_ammount);
        String topapNumber = String.valueOf(number.getText());
        String topapAmmount = String.valueOf(amount.getText());
        String dialCode = String.format("*889*4*1*%s*%s*%s#", topapNumber, topapAmmount, getResellerPIN());
        try {
            dialCode = URLEncoder.encode(dialCode,"UTF-8");
            Intent dial = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dialCode));
//            if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
            startActivity(dial);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void saveUserSettings(View view){
        EditText EVDPIN = findViewById(R.id.user_evdPIN);
        String storepin = EVDPIN.getText().toString();
        if (storepin == null) {
            Toast.makeText(this,"Nothing to save!",Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(this,"PIN "+storepin+" is saved!",Toast.LENGTH_LONG).show();
            SharedPreferences mSharedPref = getSharedPreferences("ResellerPIN", MODE_PRIVATE);
            SharedPreferences.Editor mEditor = mSharedPref.edit();
            mEditor.putInt("evdpin", Integer.parseInt(storepin));
            mEditor.apply();
        }
    }
}