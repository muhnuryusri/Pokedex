package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokedex.adapter.PokemonAdapter
import com.example.pokedex.adapter.StatsAdapter
import com.example.pokedex.adapter.TypeAdapter
import com.example.pokedex.databinding.ActivityDetailBinding
import com.example.pokedex.model.FlavorTextEntries
import com.example.pokedex.model.PokemonDetailResponse
import com.example.pokedex.model.StatsItem
import com.example.pokedex.model.TypesItem
import com.example.pokedex.viewmodel.DetailViewModel
import com.example.pokedex.viewmodel.MainViewModel
import com.example.pokedex.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var statsAdapter: StatsAdapter
    private lateinit var typeAdapter: TypeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        val extras = intent.extras
        if (extras != null) {
            val dataName = extras.getString(EXTRA_DATA)
            if (dataName != null) {
                detailViewModel.getSelectedPokemon(dataName).observe(this) { detail ->
                    populatePokemonDetailData(detail)
                }
                detailViewModel.getStatsList(dataName).observe(this) { listStats ->
                    showStatsRecyclerList(listStats)
                }
                detailViewModel.getTypesList(dataName).observe(this) { listTypes ->
                    showTypesRecyclerList(listTypes)
                }
                detailViewModel.getPokemonDesc(dataName).observe(this) { desc ->
                    populatePokemonDescData(desc)
                }
            }
        }
    }

    private fun populatePokemonDetailData(data: PokemonDetailResponse) {
        val index = data.id
        val imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"

        binding.tvName.text = data.name?.replaceFirstChar { data.name.substring(0, 1).uppercase() }
        binding.tvId.text = "#" + data.id.toString().padStart(3, '0')
        binding.tvWeight.text = data.weight.toString()
        binding.tvHeight.text = data.height.toString()
        binding.tvMoves.text = data.moves?.get(0)?.move?.name

        Glide.with(this)
            .load(imgUrl)
            .centerCrop()
            .apply(RequestOptions().override(500, 500))
            .into(binding.imgPokemon)
    }

    private fun populatePokemonDescData(descPokemon: List<FlavorTextEntries>) {
        val listDesc = ArrayList<String>()
        for (flavor_text in descPokemon) {
            listDesc.add(
                """
                ${flavor_text.flavor_text}
                """.trim()
            )
        }

        val description = descPokemon.filter { it.language.name == "en" }.distinct().take(3)
            .joinToString(" ") { it.flavor_text }.replace("[\\n\\t\\f]".toRegex(), " ")
        binding.tvDesc.text =
            description
        Log.d("texxx", listDesc.distinct().take(3).joinToString())
    }

    private fun showStatsRecyclerList(pokemonStats: List<StatsItem>) {
        statsAdapter = StatsAdapter()
        binding.rvStats.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        binding.rvStats.adapter = statsAdapter
        statsAdapter.setData(pokemonStats)
    }

    private fun showTypesRecyclerList(pokemonType: List<TypesItem>) {
        typeAdapter = TypeAdapter()
        binding.rvType.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        binding.rvType.adapter = typeAdapter
        typeAdapter.setData(pokemonType)

        val typeList = ArrayList<String>()
        for (name in pokemonType) {
            typeList.add(
                """
                ${name.type?.name}
                """.trim()
            )
        }

        when{
            typeList.first() == "poison" -> binding.detailBg.setBackgroundColor(R.color.poison)
            typeList.first() == "grass" -> binding.detailBg.setBackgroundColor(R.color.grass)
            typeList.first() == "fire" ->  binding.detailBg.setBackgroundColor(R.color.fire)
            typeList.first() == "normal" -> binding.detailBg.setBackgroundColor(R.color.normal)
            typeList.first() == "flying" -> binding.detailBg.setBackgroundColor(R.color.flying)
            typeList.first() == "rock" -> binding.detailBg.setBackgroundColor(R.color.rock)
            typeList.first() == "water" -> binding.detailBg.setBackgroundColor(R.color.water)
            typeList.first() == "ice" -> binding.detailBg.setBackgroundColor(R.color.ice)
            typeList.first() == "ghost" -> binding.detailBg.setBackgroundColor(R.color.ghost)
            typeList.first() == "steel" -> binding.detailBg.setBackgroundColor(R.color.steel)
            typeList.first() == "physic" -> binding.detailBg.setBackgroundColor(R.color.physic)
            typeList.first() == "dark" -> binding.detailBg.setBackgroundColor(R.color.dark)
            typeList.first() == "fairy" -> binding.detailBg.setBackgroundColor(R.color.fairy)
            typeList.first() == "fighting" -> binding.detailBg.setBackgroundColor(R.color.fighting)
            typeList.first() == "ground" -> binding.detailBg.setBackgroundColor(R.color.ground)
            typeList.first() == "bug" -> binding.detailBg.setBackgroundColor(R.color.bug)
            typeList.first() == "electric" -> binding.detailBg.setBackgroundColor(R.color.electric)
            typeList.first() == "dragon" -> binding.detailBg.setBackgroundColor(R.color.dragon)
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}