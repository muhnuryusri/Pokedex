package com.example.pokedex.di

import android.content.Context
import com.example.pokedex.repository.PokemonRepository
import com.example.pokedex.repository.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): PokemonRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return PokemonRepository.getInstance(remoteDataSource)
    }
}