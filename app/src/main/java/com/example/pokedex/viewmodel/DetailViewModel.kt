package com.example.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.model.FlavorTextEntries
import com.example.pokedex.model.PokemonDetailResponse
import com.example.pokedex.model.StatsItem
import com.example.pokedex.model.TypesItem
import com.example.pokedex.repository.PokemonRepository

class DetailViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {
    fun getSelectedPokemon(pokemonName: String): LiveData<PokemonDetailResponse> = pokemonRepository.getPokemonDetail(pokemonName)
    fun getStatsList(pokemonName: String): LiveData<List<StatsItem>> = pokemonRepository.getPokemonStats(pokemonName)
    fun getTypesList(pokemonName: String): LiveData<List<TypesItem>> = pokemonRepository.getPokemonType(pokemonName)
    fun getPokemonDesc(pokemonName: String): LiveData<List<FlavorTextEntries>> = pokemonRepository.getPokemonDesc(pokemonName)
}