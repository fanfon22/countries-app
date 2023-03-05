package com.juhasz.country

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.juhasz.country.data.Country

class UserAdapter(var list: List<Country>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }
    interface Dialog{
        fun onClick(position: Int)
    }

   inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var country: TextView
        var region: TextView
        var email: TextView

        init{

            country = view.findViewById(R.id.country)
            region = view.findViewById(R.id.region)
            email = view.findViewById(R.id.email)
            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.country.text = list[position].country
        holder.region.text = list[position].region
        holder.email.text = list[position].email
    }
}