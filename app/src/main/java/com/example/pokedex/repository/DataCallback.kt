package com.example.pokedex.repository

import com.example.pokedex.model.PokemonDetailResponse


interface DataCallback {
    fun onItemClicked(data: PokemonDetailResponse)
}