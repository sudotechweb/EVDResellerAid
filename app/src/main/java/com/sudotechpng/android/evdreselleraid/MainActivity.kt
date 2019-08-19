package com.sudotechpng.android.evdreselleraid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
//    lateinit var number: TextView
//    lateinit var amount: TextView
//    private var recyclerView: RecyclerView? = null
//    private var mAdapter: RecyclerView.Adapter<*>? = null
//    private var layoutManager: RecyclerView.LayoutManager? = null
//
//    //    public void setFragment(Fragment f) {
//    //        FragmentManager fm = getSupportFragmentManager();
//    //        FragmentTransaction ft = fm.beginTransaction();
//    //        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
//    //        ft.replace(R.id.frame, f);
//    //        ft.commit();
//    //    }
//
//    val resellerPIN: Int
//        get() {
//            val mSharedPref = getSharedPreferences("ResellerPIN", Context.MODE_PRIVATE)
//            return mSharedPref.getInt("evdpin", 8464)
//        }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
////        recyclerView = findViewById(R.id.frame)
////        //        recyclerView.setHasFixedSize(true);
////        layoutManager = LinearLayoutManager(this)
////        recyclerView!!.layoutManager = layoutManager
////        val input = ArrayList<String>()
////        val mString = resources.getStringArray(R.array.options)
////        for (i in mString.indices) {
////            input.add(mString[i])
////        }
////        mAdapter = MyAdapter(input)
////        recyclerView!!.adapter = mAdapter
//    }
//
//    fun dialServiceTopap(view: View) {
//        number = findViewById(R.id.topap_phone_number)
//        amount = findViewById(R.id.topap_ammount)
//        val topapNumber = number.text.toString()
//        val topapAmmount = amount.text.toString()
//        var dialCode = String.format("*889*2*%s*%s*%s#", topapNumber, topapAmmount, resellerPIN)
//        try {
//            dialCode = URLEncoder.encode(dialCode, "UTF-8")
//            val dial = Intent(Intent.ACTION_CALL, Uri.parse("tel:$dialCode"))
//            //            if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            //                return;
//            //            }
//            startActivity(dial)
//        } catch (e: UnsupportedEncodingException) {
//            e.printStackTrace()
//        }
//
//    }
//
//    fun dialServiceEasypay(view: View) {
//        number = findViewById(R.id.easypay_phone_number)
//        amount = findViewById(R.id.easypay_ammount)
//        val topapNumber = number.text.toString()
//        val topapAmmount = amount.text.toString()
//        var dialCode = String.format("*889*4*1*%s*%s*%s#", topapNumber, topapAmmount, resellerPIN)
//        try {
//            dialCode = URLEncoder.encode(dialCode, "UTF-8")
//            val dial = Intent(Intent.ACTION_CALL, Uri.parse("tel:$dialCode"))
//            //            if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            //                return;
//            //            }
//            startActivity(dial)
//        } catch (e: UnsupportedEncodingException) {
//            e.printStackTrace()
//        }
//
//    }
//
//    fun saveUserSettings(view: View) {
//        val EVDPIN = findViewById<EditText>(R.id.user_evdPIN)
//        val storepin = EVDPIN.text.toString()
//
//        Toast.makeText(this, "PIN $storepin is saved!", Toast.LENGTH_LONG).show()
//        val mSharedPref = getSharedPreferences("ResellerPIN", Context.MODE_PRIVATE)
//        val mEditor = mSharedPref.edit()
//        mEditor.putInt("evdpin", Integer.parseInt(storepin))
//        mEditor.apply()
//    }
}