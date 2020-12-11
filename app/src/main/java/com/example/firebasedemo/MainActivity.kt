package com.example.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.addData).setOnClickListener {
            var name = findViewById<EditText>(R.id.name).text.toString()
            var country = findViewById<EditText>(R.id.country).text.toString()
            FirebaseDatabase.getInstance().getReference("Players").push().setValue(Player(name,country)).addOnCompleteListener {
                Toast.makeText(this,"Data added successfully",Toast.LENGTH_SHORT).show()
                findViewById<EditText>(R.id.name).clearComposingText()
            }
        }

        findViewById<Button>(R.id.viewData).setOnClickListener {
            var intent = Intent(this,PlayerActivity::class.java)
            startActivity(intent)
        }
    }
}