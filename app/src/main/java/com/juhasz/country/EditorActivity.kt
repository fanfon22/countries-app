package com.juhasz.country

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.juhasz.country.data.AppDatabase
import com.juhasz.country.data.User

class EditorActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var btnSave: Button
    private lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        name = findViewById(R.id.name)
        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        btnSave = findViewById(R.id.btn_save)

        database = AppDatabase.getInstance(applicationContext)

        var intent = intent.extras
        if (intent!=null){
            val id = intent.getInt("id",0)
            var user = database.userDao().get(id)

            name.setText(user.name)
            username.setText(user.username)
            email.setText(user.email)
        }

        btnSave.setOnClickListener {
            if (name.text.length > 0 && username.text.length > 0 && email.text.length > 0) {

                database.userDao().insertAll(
                    User(
                        null,
                        name.text.toString(),
                        username.text.toString(),
                        email.text.toString(),
                    )
                )
                finish()
            } else {
                Toast.makeText(applicationContext, "need fill", Toast.LENGTH_SHORT).show()

            }
        }

    }

}