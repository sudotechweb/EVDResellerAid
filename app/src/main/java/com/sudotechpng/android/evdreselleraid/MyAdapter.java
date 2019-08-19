package com.sudotechpng.android.evdreselleraid;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by syagi on 01/17/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtService;
        public ImageView icoService;
        public View layout;
        public ViewHolder(View v){
            super(v);
            layout = v;
            txtService = v.findViewById(R.id.service_text);
//            icoService = (ImageView) v.findViewById(R.id.service_icon);
        }
    }

    public void add(int position, String item) {
        values.add(position,item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public MyAdapter(List<String> myDataset){
        values = myDataset;
    }
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.fragment_home,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        final String name = values.get(position);
        holder.txtService.setText(name);
        holder.txtService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),name+" clicked!",Toast.LENGTH_SHORT).show();
                FragmentManager fm = null;
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.frame, new TopapFragment());
                ft.commit();

//                remove(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return values.size();
    }
}
