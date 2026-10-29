package com.example.send_email_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val inputEmail = findViewById<EditText>(R.id.fieldEmail)
        val inputSubject = findViewById<EditText>(R.id.fieldSubject)
        val inputBody = findViewById<EditText>(R.id.fieldBody)
        val checkboxSendCopy = findViewById<CheckBox>(R.id.fieldSendCopy)
        val buttonSend = findViewById<Button>(R.id.buttonSend)

        buttonSend.setOnClickListener { view ->
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.setType("text/plain")
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(inputEmail.text.toString()))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, inputSubject.text.toString())
            emailIntent.putExtra(Intent.EXTRA_TEXT, inputBody.text)

            if (checkboxSendCopy.isChecked) {
                emailIntent.putExtra(Intent.EXTRA_CC, arrayOf("supercoolemail@yopmail.com"))
            }

            startActivity(Intent.createChooser(emailIntent, "Send e-mail.."))
        }
    }
}