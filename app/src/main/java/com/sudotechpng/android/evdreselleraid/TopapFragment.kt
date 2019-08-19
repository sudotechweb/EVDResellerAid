package com.sudotechpng.android.evdreselleraid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.fragment.app.Fragment

import java.io.UnsupportedEncodingException
import java.net.URLEncoder

import android.content.Context.MODE_PRIVATE

class TopapFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topap, container, false)
    }
}// Required empty public constructor
