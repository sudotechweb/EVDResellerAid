package com.sudotechpng.android.evdreselleraid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        QuickContactBadge qcbOnTap = (QuickContactBadge)findViewById(R.id.search_contact);
//        qcbOnTap.setOnClickListener(onQCBTouch);

        Button btn_dial = (Button)findViewById(R.id.btn_dialler);
        btn_dial.setOnClickListener(onTouchEvent);
    }

//    private View.OnClickListener onQCBTouch=new View.OnClickListener(){
//        @Override
//        public void onClick(View view) {
//            Intent lookupContact = new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_LOOKUP_URI);
//            startActivity(lookupContact);
//        }
//    };
    private View.OnClickListener onTouchEvent=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
//        variables
            EditText number = (EditText)findViewById(R.id.phone_number);
            EditText amount = (EditText)findViewById(R.id.amount);
            EditText resellerPin = (EditText)findViewById(R.id.reseller_pin);
            String getPhoneNumber = String.valueOf(number.getText());
            String getAmount = String.valueOf(amount.getText());
            String getResellerPin = String.valueOf(resellerPin.getText());
            String ussd_code = String.format("*889*2*%s*%s*%s*1", getPhoneNumber, getAmount, getResellerPin);
            Intent dial = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:"+ussd_code+"#"));
            startActivity(dial);
        };
    };
}
