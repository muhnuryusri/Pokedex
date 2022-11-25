package com.example.pokedex.service

import com.example.pokedex.model.AboutResponse
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonDetailResponse
import com.example.pokedex.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit: Int = 30,
        @Query("offset") offset: Int = 0
    ): Call<PokemonResponse>

    @GET("pokemon/{name}")
    fun getPokemonDetail(
        @Path("name") name: String
    ): Call<PokemonDetailResponse>

    @GET("pokemon-species/{name}")
    fun getPokemonDesc(
        @Path("name") pokemon_id: String
    ): Call<AboutResponse>
}