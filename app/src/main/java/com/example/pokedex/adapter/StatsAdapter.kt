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
import com.example.pokedex.databinding.ItemStatsBinding
import com.example.pokedex.model.PokemonDetailResponse
import com.example.pokedex.model.ResultsItem
import com.example.pokedex.model.StatsItem
import com.example.pokedex.model.TypeConverter
import com.example.pokedex.repository.DataCallback

class StatsAdapter : RecyclerView.Adapter<StatsAdapter.ViewHolder>() {
    private val listItems = ArrayList<StatsItem>()

    fun setData(data: List<StatsItem>) {
        listItems.clear()
        listItems.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int = listItems.size

    inner class ViewHolder(private val binding: ItemStatsBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: StatsItem) {
            with(binding) {
                tvStatName.text = data.stat?.name?.uppercase()
                tvBaseStat.text = data.baseStat.toString().padStart(3, '0')
                data.baseStat?.let { progressBar.progress = it }
            }
        }
    }

}