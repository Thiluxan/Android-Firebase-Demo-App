package com.example.firebasedemo

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

lateinit var auth: FirebaseAuth
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var emailField = findViewById<TextInputEditText>(R.id.email)
        var passwordField = findViewById<TextInputEditText>(R.id.password)

       auth = FirebaseAuth.getInstance()

        findViewById<Button>(R.id.register).setOnClickListener {
            auth.createUserWithEmailAndPassword(emailField.text.toString(),passwordField.text.toString())
                .addOnCompleteListener(this){task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        var intent = Intent(this,LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this,task.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}