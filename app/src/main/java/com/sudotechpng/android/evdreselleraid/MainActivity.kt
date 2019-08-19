package com.sudotechpng.android.evdreselleraid

import android.Manifest
import android.app.DownloadManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    private val main_bannerAdUnitId = "ca-app-pub-3511968554265090/7130367173"
    private val user_bannerAdUnitId = "ca-app-pub-3511968554265090/9855251791"
    //    private InterstitialAd muiAd;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //        // admob code
        //        String mAdMobAppId = "ca-app-pub-3511968554265090~9944410382"; // ca-app-pub-3511968554265090~9944410382
        //        MobileAds.initialize(this, mAdMobAppId);
        //        // main activity banner ad
        //        AdView mAdView = (AdView)findViewById(R.id.main_adBanner);
        //        AdRequest requestMainBanner = new AdRequest.Builder().build();
        //        mAdView.loadAd(requestMainBanner);
        //        // main-user interstitial ad
        //        muiAd = new InterstitialAd(this);
        //        String main_user_interstitialAdUnitId = "ca-app-pub-3511968554265090/7113538385";
        //        muiAd.setAdUnitId(main_user_interstitialAdUnitId);
        //        AdRequest requestInterstitial = new AdRequest.Builder().build();
        //        muiAd.loadAd(requestInterstitial);

        // send topap code
        val btn_dial = findViewById<View>(R.id.btn_dialler) as Button
        btn_dial.setOnClickListener(View.OnClickListener { view ->
            val number = findViewById<View>(R.id.phone_number) as EditText
            val amount = findViewById<View>(R.id.amount) as EditText
            val resellerPin = findViewById<View>(R.id.reseller_pin) as EditText
            val getPhoneNumber = number.text.toString()
            val getAmount = amount.text.toString()
            val getResellerPin = resellerPin.text.toString()
            var ussd_code = String.format("*889*2*%s*%s*%s#", getPhoneNumber, getAmount, getResellerPin)
            try {
                ussd_code = URLEncoder.encode(ussd_code, "UTF-8")
                val dial = Intent(Intent.ACTION_CALL, Uri.parse("tel:$ussd_code"))
                if (ActivityCompat.checkSelfPermission(view.context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return@OnClickListener
                }
                startActivity(dial)
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
        })
    }

    fun sendMessage(v: View) {
        // do something in response to button click
        val intent = Intent(this, UserSettingsActivity::class.java)
        //        if(muiAd.isLoaded()){
        //            muiAd.show();
        //        }
        startActivity(intent)
    }
}
