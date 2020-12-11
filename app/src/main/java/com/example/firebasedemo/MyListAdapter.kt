package com.example.firebasedemo

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class MyListAdapter(private val context: Activity, private val name: ArrayList<String>, private val country:ArrayList<String>)
    : ArrayAdapter<String>(context, R.layout.custome_list, name) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater!!.inflate(R.layout.custome_list, null, true)

        val nameText = rowView.findViewById(R.id.name) as TextView
        val countryText = rowView.findViewById(R.id.country) as TextView

        nameText.text = name[position]
        countryText.text = country[position]

        rowView.findViewById<Button>(R.id.delete).setOnClickListener {
            var ref = FirebaseDatabase.getInstance().getReference("Players").orderByChild("name").equalTo(name[position])
            ref.addValueEventListener(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context,"Failed to Delete Data",Toast.LENGTH_SHORT).show()
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {data ->
                        data.ref.removeValue()
                    }
                }

            })
        }

        return rowView
    }
}