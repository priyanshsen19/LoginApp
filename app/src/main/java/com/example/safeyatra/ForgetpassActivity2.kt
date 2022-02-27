package com.example.safeyatra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class ForgetpassActivity2 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var emailresetEt: EditText
    private lateinit var resettv: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgetpass2)
        emailresetEt = findViewById(R.id.regemail)
        resettv = findViewById(R.id.ResetBtn)

        auth = FirebaseAuth.getInstance()

        resettv.setOnClickListener {
            val email: String = emailresetEt.text.toString()
            if(TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else{
                auth.sendPasswordResetEmail(email).addOnCompleteListener(this, OnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(baseContext, "Password reset link sent successfully!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else {
                        Toast.makeText(baseContext, "Attempt failed!Check your email Id", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}