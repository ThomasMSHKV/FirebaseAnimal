package com.example.firebaselesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaselesson.adapter.AnimalsAdapter
import com.example.firebaselesson.databinding.ActivityMainBinding
import com.example.firebaselesson.model.AnimalData
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var mDataBase: DatabaseReference
    private lateinit var animalList: ArrayList<AnimalData>
    private lateinit var mAdapter: AnimalsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        animalList = ArrayList()
        mAdapter = AnimalsAdapter(this, animalList)
        binding.recyclerAnimals.layoutManager = LinearLayoutManager(this)
        binding.recyclerAnimals.setHasFixedSize(true)
        binding.recyclerAnimals.adapter = mAdapter
        getAnimalsData()

    }

    private fun getAnimalsData() {
        mDataBase = FirebaseDatabase.getInstance().getReference("Animals")
        mDataBase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for(animalSnapshot in snapshot.children){
                        val animal = animalSnapshot.getValue(AnimalData::class.java)
                        animalList.add(animal!!)
                    }
                    binding.recyclerAnimals.adapter = mAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity,error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}