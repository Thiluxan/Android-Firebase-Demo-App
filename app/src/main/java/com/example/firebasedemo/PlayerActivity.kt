package com.example.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.profile -> {
                Toast.makeText(this,"Profile",Toast.LENGTH_SHORT).show()
                true
            }
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()
                var intent = Intent(this,LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}