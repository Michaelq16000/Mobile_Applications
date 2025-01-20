package com.example.mobileapps2

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RecipeViewModel : ViewModel() {

    val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> get() = _recipes

    private val allRecipes = listOf(
        Recipe(1, "Black Karaage with Curry Bento", R.drawable.food, "This Japanese modern izakiya dish features crispy black ka..."),
        Recipe(2, "Bleck Karaage with Curry Bento", R.drawable.food, "This Japanese modern izakya dish features crispy black ka..."),
        Recipe(3, "Blick Karaage with Curry Bento", R.drawable.food, "This Japanese modern izakoya dish features crispy black ka..."),
        Recipe(4, "Block Karaage with Curry Bento", R.drawable.food, "This Japanese modern izakooya dish features crispy black ka..."),
        Recipe(5, "Bluck Karaage with Curry Bento", R.drawable.food, "This Japanese modern izakeya dish features crispy black ka..."),
        Recipe(6, "Blyck Karaage with Curry Bento", R.drawable.food, "This Japanese modern izakaya dish features crispy black ka...")
    )

    init {
        _recipes.value = allRecipes
    }

    fun searchRecipes(query: String) {
        if (query.length >= 3) {
            val filteredList = allRecipes.filter {
                it.name.contains(query, ignoreCase = true) || it.desc.contains(query, ignoreCase = true)
            }
            if (filteredList != _recipes.value) {
                _recipes.update { filteredList }
            }
        } else {
            _recipes.value = allRecipes
        }
    }
}
