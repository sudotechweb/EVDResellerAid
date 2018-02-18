package com.sudotechpng.android.evdreselleraid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class TransactionsFragment extends Fragment {

    public TransactionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context;
        context = getContext();
        Toast.makeText(context, "Transactions Fragments Initialized", Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_transactions, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        // is this where the dial methods will be handled?
    }
}
