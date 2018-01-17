package com.sudotechpng.android.evdreselleraid;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
//variables
    TextView number;
    TextView ammount;
    private static String natFeedUrl = "www.thenational.com.pg/feed/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            SharedPreferences msp_pin = getSharedPreferences("ResellerPIN", MODE_PRIVATE);
            int tmp_pin = msp_pin.getInt("evdpin", 8464);
            if (tmp_pin == 8464) {
//                setFragment(new TopapFragment());
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.frame, new TopapFragment());
                ft.commit();
                Toast.makeText(this, "Change your PIN in settings.", Toast.LENGTH_LONG).show();
            } else {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.frame, new TopapFragment());
                ft.commit();
            }
        }

        // admob code
        String mAdMobAppId = "ca-app-pub-3511968554265090~9944410382";
        MobileAds.initialize(this, mAdMobAppId);
        // main activity banner ad
        AdView mAdView = (AdView) findViewById(R.id.main_adBanner);
        AdRequest requestMainBanner = new AdRequest.Builder().build();
        mAdView.loadAd(requestMainBanner);
        // main-user interstitial ad
        InterstitialAd muiAd = new InterstitialAd(this);
        String main_user_interstitialAdUnitId = "ca-app-pub-3511968554265090/7113538385";
        muiAd.setAdUnitId(main_user_interstitialAdUnitId);
        AdRequest requestInterstitial = new AdRequest.Builder().build();
        muiAd.loadAd(requestInterstitial);

        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab1, R.drawable.common_full_open_on_phone, R.color.colorPrimaryText);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab2, R.drawable.common_full_open_on_phone, R.color.colorPrimaryText);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab3, R.drawable.common_full_open_on_phone, R.color.colorPrimaryText);
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.setAccentColor(Color.parseColor("#e7b9ff"));
        bottomNavigation.setInactiveColor(Color.WHITE);
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#3f51b5"));
        //  fragments and frames
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        setFragment(new TopapFragment());
                        break;
                    case 1:
                        setFragment(new EasyPayFragment());
                        break;
                    case 2:
                        setFragment(new SettingsFragment());
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Button sellEVDButton = (Button) findViewById(R.id.sendTopapDialler);
        sellEVDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Sending Topap",Toast.LENGTH_SHORT).show();
                dialServiceTopap(v);
            }
        });
    }

    public void setFragment(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        ft.replace(R.id.frame, f);
        ft.commit();
    }

    public int getResellerPIN() {
        SharedPreferences mSharedPref = getSharedPreferences("ResellerPIN", MODE_PRIVATE);
        return mSharedPref.getInt("evdpin", 8464);
    }

    public void dialServiceTopap(View view) {
        number = (TextView) findViewById(R.id.topap_phone_number);
        ammount = (TextView) findViewById(R.id.topap_ammount);
        String topapNumber = String.valueOf(number.getText());
        String topapAmmount = String.valueOf(ammount.getText());
        String dialCode = String.format("*889*2*%s*%s*%s#", topapNumber, topapAmmount, getResellerPIN());
        try {
            dialCode = URLEncoder.encode(dialCode,"UTF-8");
            Intent dial = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dialCode));
            if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(dial);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void dialServiceEasypay(View view) {
        number = (TextView) findViewById(R.id.easypay_phone_number);
        ammount = (TextView) findViewById(R.id.easypay_ammount);
        String topapNumber = String.valueOf(number.getText());
        String topapAmmount = String.valueOf(ammount.getText());
        String dialCode = String.format("*889*4*1*%s*%s*%s#", topapNumber, topapAmmount, getResellerPIN());
        try {
            dialCode = URLEncoder.encode(dialCode,"UTF-8");
            Intent dial = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dialCode));
            if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(dial);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void saveUserSettings(View view){
        EditText EVDPIN = (EditText)findViewById(R.id.user_evdPIN);
        String storepin = String.valueOf(EVDPIN.getText().toString());
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