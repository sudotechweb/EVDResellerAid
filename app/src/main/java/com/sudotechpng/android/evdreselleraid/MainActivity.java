package com.sudotechpng.android.evdreselleraid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(dial);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
