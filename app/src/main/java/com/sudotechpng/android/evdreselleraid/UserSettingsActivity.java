package com.sudotechpng.android.evdreselleraid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class UserSettingsActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private EditText EVDPIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        // admob code
        String mAdMobAppId = "ca-app-pub-3511968554265090~9944410382";
        MobileAds.initialize(this, mAdMobAppId);
        // main activity banner ad
        AdView mAdView = (AdView)findViewById(R.id.user_adBanner);
        AdRequest requestUserBanner = new AdRequest.Builder().build();
        mAdView.loadAd(requestUserBanner);

        Button saveUser = (Button)findViewById(R.id.saveUserBtn);
        saveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserSettings();
            }
        });
    }

    private void saveUserSettings(){
        EVDPIN = (EditText)findViewById(R.id.user_evdPIN);
        String storepin = String.valueOf(EVDPIN.getText().toString());
        Toast.makeText(this,"PIN "+storepin+" is saved!",Toast.LENGTH_LONG).show();

        SharedPreferences mSharedPref = getSharedPreferences("ResellerPIN", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPref.edit();
        mEditor.putInt("evdpin", Integer.parseInt(storepin));
        mEditor.apply();
    }

    private int getResellerPIN(){
        SharedPreferences mSharedPref = getSharedPreferences("ResellerPIN", MODE_PRIVATE);
        return mSharedPref.getInt("evdpin", 8464);
    }
}
