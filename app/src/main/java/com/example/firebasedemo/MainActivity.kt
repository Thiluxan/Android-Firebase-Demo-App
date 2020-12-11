package com.example.firebasedemo

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
            FirebaseDatabase.getInstance().getReference("Name").setValue(name).addOnCompleteListener {
                Toast.makeText(this,"Data added successfully",Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.viewData).setOnClickListener {
            var output = findViewById<TextView>(R.id.output)
            FirebaseDatabase.getInstance().getReference("Name").addValueEventListener(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@MainActivity,"Failed to load database",Toast.LENGTH_SHORT).show()
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    output.text = snapshot.getValue().toString()
                }

            })
        }
    }
}