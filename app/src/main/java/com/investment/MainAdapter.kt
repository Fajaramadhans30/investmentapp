package com.investment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainAdapter(private val context: Context,
                  private val title: ArrayList<String>) :
    RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
//    var title: ArrayList<String>? = null
//    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // infalte the item Layout
        val v: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.invest_type_item_layout, parent, false)
        return MyViewHolder(v) // pass the view to View Holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // set the data in items
        holder.tvTitle.text = title[position]
        // implement setOnClickListener event on item view.
//        holder.itemView.setOnClickListener(View.OnClickListener { // display a toast with person name on item click
//            Toast.makeText(context, personNames!![position], Toast.LENGTH_SHORT).show()
//        })
    }


    override fun getItemCount(): Int {
        return title.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById<View>(R.id.tvTitle) as TextView
    }
}