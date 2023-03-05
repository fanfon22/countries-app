package com.juhasz.country

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.juhasz.country.data.AppDatabase
import com.juhasz.country.data.Country

class EditorActivity : AppCompatActivity() {
    private lateinit var country: EditText
    private lateinit var region: EditText
    private lateinit var email: EditText
    private lateinit var btnSave: Button
    private lateinit var database: AppDatabase
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        country = findViewById(R.id.country)
        region = findViewById(R.id.region)
        email = findViewById(R.id.email)
        btnSave = findViewById(R.id.btn_save)

        database = AppDatabase.getInstance(applicationContext)

        var intent = intent.extras
        if (intent!=null){
            val id = intent.getInt("id",0)
            var user = database.userDao().get(id)

            country.setText(user.country)
            region.setText(user.region)
            email.setText(user.email)
        }

        btnSave.setOnClickListener {
            if (country.text.length > 0 && region.text.length > 0 && email.text.length > 0) {
                if(intent!=null){
                    database.userDao().update(
                        Country(
                            intent.getInt("id",0),
                            country.text.toString(),
                            region.text.toString(),
                            email.text.toString(),
                        )
                    )
                } else{
                    database.userDao().insertAll(
                        Country(
                            null,
                            country.text.toString(),
                            region.text.toString(),
                            email.text.toString(),
                        )
                    )
                }

                finish()
            } else {
                Toast.makeText(applicationContext, "need fill", Toast.LENGTH_SHORT).show()

            }
        }

    }

}