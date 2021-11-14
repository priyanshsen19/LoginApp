package com.example.safeyatra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.example.safeyatra.databinding.ActivitySignupBinding
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivitySignupBinding
    private lateinit var database : DatabaseReference

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText

    private lateinit var signUpBtn: Button
    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginBtn = findViewById(R.id.login_btn)

        binding.loginBtn.setOnClickListener {

            val name = binding.name.text.toString()
            val emergencycontact = binding.emergencycontact.text.toString()
            val phoneno = binding.phoneno.text.toString()
            val emailid = binding.email.text.toString()
            val password = binding.pass.text.toString()


            database = FirebaseDatabase.getInstance().getReference("Users")
            val User = User(name,emergencycontact, phoneno,emailid, password)
            database.child(name).setValue(User).addOnSuccessListener {
                Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
            binding.email.text.clear()
            binding.emergencycontact.text.clear()
            binding.phoneno.text.clear()
            binding.pass.text.clear()

        }
    }
}