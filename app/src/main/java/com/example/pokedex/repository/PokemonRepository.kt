package com.example.pokedex.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokedex.model.*

class PokemonRepository private constructor(private val remoteDataSource: RemoteDataSource) : PokemonDataSource{
    companion object {
        @Volatile
        private var instance: PokemonRepository? = null

        fun getInstance(remoteData: RemoteDataSource): PokemonRepository =
            instance ?: synchronized(this) {
                instance ?: PokemonRepository(remoteData)
            }
    }

    override fun getPokemon(): LiveData<List<PokemonDetailResponse>> {
        val pokemonItems = MutableLiveData<List<PokemonDetailResponse>>()

        remoteDataSource.getPokemon(object : RemoteDataSource.LoadPokemonCallback {
            override fun onPokemonLoaded(pokemonList: List<PokemonDetailResponse>?) {
                val pokemonArrayList = ArrayList<PokemonDetailResponse>()
                if (pokemonList != null) {
                    for (response in pokemonList) {
                        with(response) {
                            val pokemon = PokemonDetailResponse(name)
                            pokemonArrayList.add(pokemon)
                        }
                    }
                    pokemonItems.postValue(pokemonList)
                }
            }
        })
        return pokemonItems
    }

    override fun getPokemonSearch(pokemonName: String): LiveData<List<PokemonDetailResponse>> {
        val pokemonItems = MutableLiveData<List<PokemonDetailResponse>>()

        remoteDataSource.getPokemonSearch(object : RemoteDataSource.LoadPokemonCallback {
            override fun onPokemonLoaded(pokemonList: List<PokemonDetailResponse>?) {
                val pokemonArrayList = ArrayList<PokemonDetailResponse>()
                if (pokemonList != null) {
                    for (response in pokemonList) {
                        with(response) {
                            val pokemon = PokemonDetailResponse(name)
                            pokemonArrayList.add(pokemon)
                        }
                    }
                    pokemonItems.postValue(pokemonList)
                }
            }
        })
        return pokemonItems
    }

    override fun getPokemonStats(pokemonName: String): LiveData<List<StatsItem>> {
        val pokemonStats = MutableLiveData<List<StatsItem>>()

        remoteDataSource.getPokemonStats(object : RemoteDataSource.LoadPokemonStatsCallback {
            override fun onPokemonStatsLoaded(statsList: List<StatsItem>?) {
                val statsArrayList = ArrayList<StatsItem>()
                if (statsList != null) {
                    for (response in statsList) {
                        with(response) {
                            val stat = StatsItem(stat)
                            statsArrayList.add(stat)
                        }
                    }
                    pokemonStats.postValue(statsList)
                }
            }
        }, pokemonName)
        return pokemonStats
    }

    override fun getPokemonType(pokemonName: String): LiveData<List<TypesItem>> {
        val pokemonType = MutableLiveData<List<TypesItem>>()

        remoteDataSource.getPokemonTypes(object : RemoteDataSource.LoadPokemonTypesCallback {
            override fun onPokemonTypesLoaded(typesList: List<TypesItem>?) {
                val typesArrayList = ArrayList<TypesItem>()
                if (typesList != null) {
                    for (response in typesList) {
                        with(response) {
                            val type = TypesItem(slot, type)
                            typesArrayList.add(type)
                        }
                    }
                    pokemonType.postValue(typesList)
                }
            }
        }, pokemonName)
        return pokemonType
    }

    override fun getPokemonDesc(pokemonName: String): LiveData<List<FlavorTextEntries>> {
        val pokemonDescItem = MutableLiveData<List<FlavorTextEntries>>()

        remoteDataSource.getPokemonDesc(object : RemoteDataSource.LoadPokemonDescCallback {
            override fun onPokemonDescLoaded(pokemonDesc: List<FlavorTextEntries>?) {
                val descArrayList = ArrayList<FlavorTextEntries>()
                if (pokemonDesc != null) {
                    for (response in pokemonDesc) {
                        with(response) {
                            val desc = FlavorTextEntries(flavor_text, language)
                            descArrayList.add(desc)
                        }
                    }
                    pokemonDescItem.postValue(pokemonDesc)
                }
            }
        }, pokemonName)
        return pokemonDescItem
    }

    override fun getPokemonDetail(pokemonName: String): LiveData<PokemonDetailResponse> {
        val pokemonDetailItems = MutableLiveData<PokemonDetailResponse>()

        remoteDataSource.getPokemonDetail(object : RemoteDataSource.LoadPokemonDetailCallback {
            override fun onPokemonDetailLoaded(pokemonDetail: PokemonDetailResponse?) {
                if (pokemonDetail != null) {
                    with(pokemonDetail) {

                        val detailPokemon = PokemonDetailResponse(
                            id = id,
                            name = name,
                            weight = weight,
                            height = height,
                        )
                        pokemonDetailItems.postValue(detailPokemon)
                    }
                }
            }
        }, pokemonName)
        return pokemonDetailItems
    }
}