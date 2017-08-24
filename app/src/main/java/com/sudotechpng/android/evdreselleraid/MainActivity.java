package com.sudotechpng.android.evdreselleraid;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private String mAdMobAppId = "ca-app-pub-3511968554265090~9944410382";
    private String main_bannerAdUnitId = "ca-app-pub-3511968554265090/7130367173";
    private String main_user_interstitialAdUnitId = "ca-app-pub-3511968554265090/7113538385";
    private String user_bannerAdUnitId = "ca-app-pub-3511968554265090/9855251791";
    private InterstitialAd muiAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // admob code
        MobileAds.initialize(this,mAdMobAppId);
        // main activity banner ad
        AdView mAdView = (AdView)findViewById(R.id.main_adBanner);
        AdRequest requestMainBanner = new AdRequest.Builder().build();
        mAdView.loadAd(requestMainBanner);
        // main-user interstitial ad
        muiAd = new InterstitialAd(this);
        muiAd.setAdUnitId(main_user_interstitialAdUnitId);
        AdRequest requestInterstitial = new AdRequest.Builder().build();
        muiAd.loadAd(requestInterstitial);

        // send topap code
        Button btn_dial = (Button) findViewById(R.id.btn_dialler);
        btn_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText number = (EditText) findViewById(R.id.phone_number);
                EditText amount = (EditText) findViewById(R.id.amount);
                EditText resellerPin = (EditText) findViewById(R.id.reseller_pin);
                String getPhoneNumber = String.valueOf(number.getText());
                String getAmount = String.valueOf(amount.getText());
                String getResellerPin = String.valueOf(resellerPin.getText());
                String ussd_code = String.format("*889*2*%s*%s*%s#", getPhoneNumber, getAmount, getResellerPin);
                try {
                    ussd_code = URLEncoder.encode(ussd_code, "UTF-8");
                    Intent dial = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ussd_code));
                    if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(dial);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendMessage(View v){
        // do something in response to button click
        Intent intent = new Intent(this, UserSettingsActivity.class);
        if(muiAd.isLoaded()){
            muiAd.show();
        }
        startActivity(intent);
    }
}
