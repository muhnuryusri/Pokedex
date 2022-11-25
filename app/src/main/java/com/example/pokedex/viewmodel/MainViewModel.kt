package com.example.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.model.PokemonDetailResponse
import com.example.pokedex.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {
    fun getPokemonList() = pokemonRepository.getPokemon()
    fun getPokemonSearch(pokemonName: String) = pokemonRepository.getPokemonDetail(pokemonName)
}