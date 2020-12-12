package com.example.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        if(FirebaseAuth.getInstance().currentUser != null){
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.registerActivity).setOnClickListener {
            var intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        var emailField = findViewById<TextInputEditText>(R.id.email)
        var passwordField = findViewById<TextInputEditText>(R.id.password)

        findViewById<Button>(R.id.login).setOnClickListener {
            auth.signInWithEmailAndPassword(emailField.text.toString(),passwordField.text.toString())
                    .addOnCompleteListener(this){task ->
                        if(task.isSuccessful){
                            var intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this,"Failed to Login\nCheck your credentials",Toast.LENGTH_SHORT).show()
                        }
                    }
        }
    }
}