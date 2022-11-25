package com.example.pokedex.repository

import android.util.Log
import com.example.pokedex.model.*
import com.example.pokedex.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getPokemon(callback: LoadPokemonCallback) {
        val client = ApiClient.getApiService().getPokemonList()
        client.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                callback.onPokemonLoaded(response.body()?.results as List<PokemonDetailResponse>?)
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovies onFailure : ${t.message}")
            }
        })
    }

    fun getPokemonSearch(callback: LoadPokemonCallback) {
        val client = ApiClient.getApiService().getPokemonList()
        client.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                callback.onPokemonLoaded(response.body()?.results as List<PokemonDetailResponse>?)
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovies onFailure : ${t.message}")
            }
        })
    }

    fun getPokemonStats(callback: LoadPokemonStatsCallback, pokemon: String) {
        val client = ApiClient.getApiService().getPokemonDetail(pokemon)
        client.enqueue(object : Callback<PokemonDetailResponse> {
            override fun onResponse(call: Call<PokemonDetailResponse>, response: Response<PokemonDetailResponse>) {
                callback.onPokemonStatsLoaded(response.body()?.stats as List<StatsItem>?)
            }

            override fun onFailure(call: Call<PokemonDetailResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovieDetail onFailure : ${t.message}")
            }
        })
    }

    fun getPokemonTypes(callback: LoadPokemonTypesCallback, pokemon: String) {
        val client = ApiClient.getApiService().getPokemonDetail(pokemon)
        client.enqueue(object : Callback<PokemonDetailResponse> {
            override fun onResponse(call: Call<PokemonDetailResponse>, response: Response<PokemonDetailResponse>) {
                callback.onPokemonTypesLoaded(response.body()?.types as List<TypesItem>?)
            }

            override fun onFailure(call: Call<PokemonDetailResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovieDetail onFailure : ${t.message}")
            }
        })
    }

    fun getPokemonDesc(callback: LoadPokemonDescCallback, pokemon: String) {
        val client = ApiClient.getApiService().getPokemonDesc(pokemon)
        client.enqueue(object : Callback<AboutResponse> {
            override fun onResponse(call: Call<AboutResponse>, response: Response<AboutResponse>) {
                callback.onPokemonDescLoaded(response.body()?.flavor_text_entries)
            }

            override fun onFailure(call: Call<AboutResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovieDetail onFailure : ${t.message}")
            }
        })
    }

    fun getPokemonDetail(callback: LoadPokemonDetailCallback, pokemon: String) {
        val client = ApiClient.getApiService().getPokemonDetail(pokemon)
        client.enqueue(object : Callback<PokemonDetailResponse> {
            override fun onResponse(call: Call<PokemonDetailResponse>, response: Response<PokemonDetailResponse>) {
                callback.onPokemonDetailLoaded(response.body())
            }

            override fun onFailure(call: Call<PokemonDetailResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovieDetail onFailure : ${t.message}")
            }
        })
    }

    interface LoadPokemonCallback {
        fun onPokemonLoaded(pokemonList : List<PokemonDetailResponse>?)
    }

    interface LoadPokemonDetailCallback {
        fun onPokemonDetailLoaded(pokemonDetail: PokemonDetailResponse?)
    }

    interface LoadPokemonStatsCallback {
        fun onPokemonStatsLoaded(statsList : List<StatsItem>?)
    }

    interface LoadPokemonTypesCallback {
        fun onPokemonTypesLoaded(typesList : List<TypesItem>?)
    }

    interface LoadPokemonDescCallback {
        fun onPokemonDescLoaded(pokemonDesc : List<FlavorTextEntries>?)
    }
}