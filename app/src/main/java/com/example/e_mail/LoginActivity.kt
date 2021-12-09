package com.example.e_mail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.*

class LoginActivity : AppCompatActivity() {

    private lateinit var  editTextEmailAddress: EditText
    private lateinit var  editTextPassword: EditText
    private lateinit var  editTextRepeatPassword: EditText
    private lateinit var  buttonSubmit: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
        registerListener()

    }
    private fun init() {
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextRepeatPassword = findViewById(R.id.editTextRepeatPassword)
        buttonSubmit = findViewById(R.id.buttonSubmit)
    }



    private fun registerListener() {
        buttonSubmit.setOnClickListener {
            val email = editTextEmailAddress.text.toString()
            val password = editTextPassword.text.toString()
            val repeatPassword = editTextRepeatPassword.text.toString()

            if(email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                Toast.makeText(this,"Empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(!(email.contains("@")) || email.length < 6){
                Toast.makeText(this,"Incorrect email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(!(password.matches("123456789".toRegex()) || !(password.matches(".*[!@#$%^&*()].*".toRegex()))) || password.length < 9) {
                Toast.makeText(this,"Password is not Valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(password != repeatPassword){
                Toast.makeText(this,"Passwords don't match", Toast.LENGTH_SHORT).show()
            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
        }

    }


}