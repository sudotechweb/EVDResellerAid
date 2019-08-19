package com.sudotechpng.android.evdreselleraid

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView

import org.w3c.dom.Text

/**
 * Created by syagi on 01/17/2018.
 */

class MyAdapter(private val values: MutableList<String>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(var layout: View) : RecyclerView.ViewHolder(layout) {
        var txtService: TextView
        var icoService: ImageView? = null

        init {
            txtService = layout.findViewById(R.id.service_text)
            //            icoService = (ImageView) v.findViewById(R.id.service_icon);
        }
    }

    fun add(position: Int, item: String) {
        values.add(position, item)
        notifyItemInserted(position)
    }

    fun remove(position: Int) {
        values.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.fragment_home, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = values[position]
        holder.txtService.text = name
        holder.txtService.setOnClickListener { v ->
            Toast.makeText(v.context, "$name clicked!", Toast.LENGTH_SHORT).show()
            val fm: FragmentManager? = null
            val ft = fm!!.beginTransaction()
            ft.add(R.id.frame, TopapFragment())
            ft.commit()

            //                remove(position);
        }
    }

    override fun getItemCount(): Int {
        return values.size
    }
}
