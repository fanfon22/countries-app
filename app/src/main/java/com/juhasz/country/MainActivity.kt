package com.juhasz.country

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.juhasz.country.data.AppDatabase
import com.juhasz.country.data.Country
import com.juhasz.country.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RVAdapter;
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list = mutableListOf<Country>()
    private lateinit var adapter2: UserAdapter;
    private lateinit var database: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = findViewById(R.id.rv_main)
        recyclerView = findViewById(R.id.tv_main)
        fab = findViewById(R.id.fab)

        database = AppDatabase.getInstance(applicationContext)
        adapter2 = UserAdapter(list)
        adapter2.setDialog(object : UserAdapter.Dialog{
            override fun onClick(position: Int) {
                val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setTitle(list[position].country)
                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener{ dialog, which ->
                    if (which==0){
                        val intent = Intent(this@MainActivity, EditorActivity::class.java)
                        intent.putExtra("id", list[position].uid)
                        startActivity(intent)
                    } else if(which==1){
                        database.userDao().delete(list[position])
                        getData()
                    }else {
                        dialog.dismiss()
                    }
                    
                })
                val dialogView = dialog.create()
                dialogView.show()
            }

        })

        recyclerView.adapter = adapter2
        recyclerView.layoutManager = LinearLayoutManager(applicationContext,VERTICAL,false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))


        adapter = RVAdapter(this@MainActivity, arrayListOf())

        binding.rvMain.adapter = adapter
        binding.rvMain.setHasFixedSize(true)

        remoteGetUsers()

        fab.setOnClickListener{
            startActivity(Intent(this,EditorActivity::class.java))
        }
    }

    override fun onResume(){
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.userDao().getAll())
        adapter2.notifyDataSetChanged()
    }

       fun remoteGetUsers(){
           ApiClient.apiService.getUsers().enqueue(object : Callback<ArrayList<UserModel>> {
               override fun onResponse(
                   call: Call<ArrayList<UserModel>>,
                   response: Response<ArrayList<UserModel>>
               ) {
                   if(response.isSuccessful){
                       val data = response.body()
                       setDataToAdapter(data!!)
                   }
               }

               override fun onFailure(call: Call<ArrayList<UserModel>>, t: Throwable) {
                   Log.d("Error",""+t.stackTraceToString())
               }
           })
       }
    fun setDataToAdapter(data:ArrayList<UserModel>){
       adapter.setData(data)
    }
    }
