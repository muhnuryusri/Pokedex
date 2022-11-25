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
import com.example.pokedex.databinding.ItemPokemonBinding
import com.example.pokedex.model.PokemonDetailResponse
import com.example.pokedex.model.ResultsItem
import com.example.pokedex.model.TypeConverter
import com.example.pokedex.repository.DataCallback

class PokemonAdapter(private val callback: DataCallback) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    private val listItems = ArrayList<PokemonDetailResponse>()

    fun setData(data: List<PokemonDetailResponse>) {
        listItems.clear()
        listItems.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int = listItems.size

    inner class ViewHolder(private val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: PokemonDetailResponse) {
            with(binding) {
                val index = data.url?.split("/".toRegex())?.dropLast(1)?.last()
                val imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
                Glide.with(itemView.context)
                    .load(imgUrl)
                    .centerCrop()
                    .apply(RequestOptions().override(300, 300))
                    .into(imgPokemon)
                tvNumber.text = "#" + index?.padStart(3, '0')
                tvName.text = data.name?.replaceFirstChar { data.name.substring(0, 1).uppercase() }

                item.setOnClickListener {
                    callback.onItemClicked(data)
                }
            }
        }
    }

}