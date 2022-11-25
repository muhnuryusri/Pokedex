package com.example.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex.adapter.PokemonAdapter
import com.example.pokedex.databinding.ActivityDetailBinding
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.model.PokemonDetailResponse
import com.example.pokedex.repository.DataCallback
import com.example.pokedex.viewmodel.MainViewModel
import com.example.pokedex.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity(), DataCallback {
    private lateinit var adapter: PokemonAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        viewModel.getPokemonList().observe(this) { listPokemon ->
            binding.recyclerView.adapter?.let { adapter ->
                when (adapter) {
                    is PokemonAdapter -> adapter.setData(listPokemon)
                }
            }
        }
        showRecyclerList()

        binding.searchPokemon.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()) {
                    return true
                } else {
                    binding.recyclerView.adapter?.notifyDataSetChanged()
                    viewModel.getPokemonSearch(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun showRecyclerList() {
        adapter = PokemonAdapter(this@MainActivity)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.recyclerView.adapter = adapter
    }

    override fun onItemClicked(data: PokemonDetailResponse) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, data.name)
        this.startActivity(intent)
    }
}