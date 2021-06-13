package com.example.firebaselesson.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaselesson.R
import com.example.firebaselesson.databinding.ItemListBinding
import com.example.firebaselesson.model.AnimalData

class AnimalsAdapter(var c: Context, var animalsList:ArrayList<AnimalData>): RecyclerView.Adapter<AnimalsAdapter.AnimalViewHolder>()
{
    inner class AnimalViewHolder(var v: ItemListBinding): RecyclerView.ViewHolder(v.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<ItemListBinding>(inflater, R.layout.item_list, parent,false)

        return AnimalViewHolder(v)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.v.isAnimals = animalsList[position]
    }

    override fun getItemCount(): Int {
        return animalsList.size
    }
}