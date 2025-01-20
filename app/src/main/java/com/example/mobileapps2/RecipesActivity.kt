package com.example.mobileapps2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch

class RecipesActivity : AppCompatActivity() {

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        val recipesRecyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recipeRecyclerView)
        val recipeAdapter = RecipesAdapter(this, emptyList())

        setupRecyclerView(recipesRecyclerView, recipeAdapter)
        refreshRecipes(recipeAdapter)

        val searchView = findViewById<androidx.appcompat.widget.SearchView>(R.id.searchView)
        setupSearchView(searchView)
    }

    private fun setupRecyclerView(recyclerView: androidx.recyclerview.widget.RecyclerView, adapter: RecipesAdapter) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun refreshRecipes(adapter: RecipesAdapter) {
        lifecycleScope.launch {
            viewModel.recipes.collect { recipes ->
                adapter.updateRecipes(recipes)
            }
        }
    }

    private fun setupSearchView(searchView: androidx.appcompat.widget.SearchView) {
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchRecipes(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchRecipes(it) }
                return true
            }
        })
    }
}
