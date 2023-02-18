package com.juhasz.country

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class EditorActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var btnSave: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        name = findViewById(R.id.name)
        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        btnSave = findViewById(R.id.btn_save)
    }
}