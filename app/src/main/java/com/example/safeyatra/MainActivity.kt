package com.example.safeyatra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText

    private lateinit var loginBtn: Button
    private lateinit var signupBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailEt = findViewById(R.id.edt1)
        passwordEt = findViewById(R.id.edt2)
        loginBtn= findViewById(R.id.secondActivityBtn)
        signupBtn= findViewById(R.id.signup_btn)

        auth = FirebaseAuth.getInstance()

        signupBtn.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
        loginBtn.setOnClickListener {
            val email: String = emailEt.text.toString()
            val password: String = passwordEt.text.toString()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else{
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(baseContext, "Successfully Logged In", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else {
                        Toast.makeText(baseContext, "Login Failed", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    fun forgetpass(view: View) {

        val forget: TextView = findViewById(R.id.reset_pass_tv)
        forget.setOnClickListener {
            val intent = Intent(this, ForgetpassActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }
}