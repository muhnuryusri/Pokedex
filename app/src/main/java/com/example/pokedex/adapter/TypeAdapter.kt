package com.example.pokedex.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokedex.R
import com.example.pokedex.databinding.ItemPokemonBinding
import com.example.pokedex.databinding.ItemStatsBinding
import com.example.pokedex.databinding.ItemTypeBinding
import com.example.pokedex.model.*
import com.example.pokedex.repository.DataCallback

class TypeAdapter : RecyclerView.Adapter<TypeAdapter.ViewHolder>() {
    private val listItems = ArrayList<TypesItem>()

    fun setData(data: List<TypesItem>) {
        listItems.clear()
        listItems.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int = listItems.size

    inner class ViewHolder(private val binding: ItemTypeBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: TypesItem) {
            with(binding) {
                val type = data.type?.name
                tvType.text = type

                when(type){
                    "poison" -> cvTypes.setCardBackgroundColor(R.color.poison)
                    "grass" -> cvTypes.setCardBackgroundColor(R.color.grass)
                    "fire" ->  cvTypes.setCardBackgroundColor(R.color.fire)
                    "normal" -> cvTypes.setCardBackgroundColor(R.color.normal)
                    "flying" -> cvTypes.setCardBackgroundColor(R.color.flying)
                    "rock" -> cvTypes.setCardBackgroundColor(R.color.rock)
                    "water" -> cvTypes.setCardBackgroundColor(R.color.water)
                    "ice" -> cvTypes.setCardBackgroundColor(R.color.ice)
                    "ghost" -> cvTypes.setCardBackgroundColor(R.color.ghost)
                    "steel" -> cvTypes.setCardBackgroundColor(R.color.steel)
                    "physic" -> cvTypes.setCardBackgroundColor(R.color.physic)
                    "dark" -> cvTypes.setCardBackgroundColor(R.color.dark)
                    "fairy" -> cvTypes.setCardBackgroundColor(R.color.fairy)
                    "fighting" -> cvTypes.setCardBackgroundColor(R.color.fighting)
                    "ground" -> cvTypes.setCardBackgroundColor(R.color.ground)
                    "bug" -> cvTypes.setCardBackgroundColor(R.color.bug)
                    "electric" -> cvTypes.setCardBackgroundColor(R.color.electric)
                    "dragon" -> cvTypes.setCardBackgroundColor(R.color.dragon)
                }
            }
        }
    }

}