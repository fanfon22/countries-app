package com.juhasz.country

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.juhasz.country.data.User
import com.juhasz.country.R;

class UserAdapter(var list: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }
    interface Dialog{
        fun onClick(position: Int)
    }

   inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var name: TextView
        var username: TextView
        var email: TextView

        init{

            name = view.findViewById(R.id.name)
            username = view.findViewById(R.id.username)
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
        holder.name.text = list[position].name
        holder.username.text = list[position].username
        holder.email.text = list[position].email
    }
}