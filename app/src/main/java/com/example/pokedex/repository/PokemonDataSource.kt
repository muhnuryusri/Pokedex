package com.example.pokedex.repository

import androidx.lifecycle.LiveData
import com.example.pokedex.model.*

interface PokemonDataSource {
    fun getPokemon(): LiveData<List<PokemonDetailResponse>>
    fun getPokemonSearch(pokemonName: String): LiveData<List<PokemonDetailResponse>>
    fun getPokemonDetail(pokemonName: String): LiveData<PokemonDetailResponse>
    fun getPokemonStats(pokemonName: String): LiveData<List<StatsItem>>
    fun getPokemonType(pokemonName: String): LiveData<List<TypesItem>>
    fun getPokemonDesc(pokemonName: String): LiveData<List<FlavorTextEntries>>
}