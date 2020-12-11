package com.example.firebasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        FirebaseDatabase.getInstance().getReference("Players").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@PlayerActivity,"Failed to load database",Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var nameList = ArrayList<String>()
                var countryList = ArrayList<String>()

                snapshot.children.forEach {data ->
                    var player = data.getValue(Player::class.java)
                    nameList.add(player?.name.toString())
                    countryList.add(player?.country.toString())
                }

                var myListAdapter =  MyListAdapter(this@PlayerActivity,nameList!!,countryList!!)
                findViewById<ListView>(R.id.player_list).adapter = myListAdapter
            }

        })
    }
}