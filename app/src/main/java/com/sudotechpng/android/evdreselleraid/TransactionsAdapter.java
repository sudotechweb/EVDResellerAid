package com.sudotechpng.android.evdreselleraid;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by syagi on 01/21/2018.
 */

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {
    private List<Transaction> values;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tName;
        public Button tBtn;
        public EditText tNbr;
        public EditText tAmt;
        public View layout;

        public ViewHolder (View v) {
            super(v);
            layout = v;
            tName = (TextView) v.findViewById(R.id.transaction_name);
            tBtn = (Button) v.findViewById(R.id.process_transaction_btn);
            tNbr = (EditText) v.findViewById(R.id.transaction_number);
            tAmt = (EditText) v.findViewById(R.id.transaction_amount);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Button Clicked", Toast.LENGTH_SHORT).show();
            Log.d("ONCLICK","onclick() "+getItemCount()+" Count");
        }
    }
    public void add(int position, Transaction item){
        values.add(position,item);
        notifyItemInserted(position);
    }
    public void remove(int position){
        values.remove(position);
        notifyItemRemoved(position);
    }
    public TransactionsAdapter(List<Transaction> myDataset){
        values = myDataset;
    }

    @Override
    public TransactionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.fragment_transactions,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override

    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Transaction name = values.get(position);
        holder.tName.setText(name.getName());
        holder.tNbr.setHint(name.getTransNbr());
        holder.tAmt.setHint(name.getTransAmt());
//        holder.tBtn.setOnClickListener(dialServiceTopap());
        holder.tBtn.setText("Send Topap");
        holder.tBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView transNbr;
                TextView transAmt;
                String transTo;
                String transVal;
//                Toast.makeText(v.getContext(), "show me on btn click "+name.getOnClickMethod(), Toast.LENGTH_SHORT).show();
                transNbr = (TextView) v.findViewById(R.id.transaction_number);
                transAmt = (TextView) v.findViewById(R.id.transaction_amount);
                transTo = String.valueOf(transNbr);
                transVal = String.valueOf(transAmt);
                Toast.makeText(v.getContext(), "send to "+transTo+" "+transVal, Toast.LENGTH_LONG).show();
                String dialCode = String.format("*889*2*%s*%s*2314#", "71387187", "2");
                try {
                    dialCode = URLEncoder.encode(dialCode,"UTF-8");
                    Intent dial = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + dialCode));
                    if (ActivityCompat.checkSelfPermission(v.getContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(v.getContext(), "Grant app permission to dial!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    startActivity(v.getContext(), dial, null);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if(name.getOnClickMethod() == "dialServiceTopap") {
                    Toast.makeText(v.getContext(), name.getOnClickMethod(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
