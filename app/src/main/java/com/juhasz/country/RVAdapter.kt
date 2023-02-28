package com.juhasz.country

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(
    private val context: Context,
    private val dataList: ArrayList<UserModel>
): RecyclerView.Adapter<RVAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val capital = view.findViewById<TextView>(R.id.capital)
        val region = view.findViewById<TextView>(R.id.region)
        val subregion = view.findViewById<TextView>(R.id.subregion)
        val lang = view.findViewById<TextView>(R.id.lang)
        val cvMain = view.findViewById<CardView>(R.id.cv_main)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.items_main,parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.capital.text = dataList.get(position).capital
        holder.region.text = dataList.get(position).region
        holder.subregion.text = dataList.get(position).subregion
        holder.lang.text = dataList.get(position).lang
        holder.cvMain.setOnClickListener{
            Toast.makeText(context,""+dataList.get(position).region,Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(data: ArrayList<UserModel>){
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }
}