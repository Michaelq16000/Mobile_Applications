package com.example.mobileapps2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecipesAdapter(
    private val context: Context,
    private var recipes: List<Recipe>
) : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipesAdapter.ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)

        holder.itemView.setOnClickListener {
            Log.d("itemView", "${recipe.name} clicked")
        }

        holder.share.setOnClickListener {
            Toast.makeText(context, "Shared ${recipe.name}", Toast.LENGTH_SHORT).show()
        }

        holder.like.setOnClickListener {
            Toast.makeText(context, "Liked ${recipe.name}", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateRecipes(newRecipes: List<Recipe>) {
        if (newRecipes != recipes) {
            recipes = newRecipes
            notifyDataSetChanged()
            Log.d("RecipesAdapter", "Updating recipes with size: ${newRecipes.size}")
        }
        Log.d("RecipesAdapter", "Updating recipes with size: ${newRecipes.size}")
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)
        private val description: TextView = itemView.findViewById(R.id.description)
        private val image: ImageView = itemView.findViewById(R.id.image)
        val share: ImageButton = itemView.findViewById(R.id.shareButton)
        val like: ImageButton = itemView.findViewById(R.id.likeButton)

        fun bind(recipe: Recipe) {
            title.text = recipe.name
            description.text = recipe.desc
            image.setImageResource(recipe.imageId)
        }
    }

    override fun getItemCount(): Int = recipes.size
}