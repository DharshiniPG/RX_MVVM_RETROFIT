package com.example.rx_mvvm_retrofit.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rx_mvvm_retrofit.databinding.MoviesCardViewBinding
import com.example.rx_mvvm_retrofit.model.HeroClass
import com.example.rx_mvvm_retrofit.viewmodal.MoviesCardViewItemViewModal
import java.util.*

class MoviesAdapter(private val context: Context) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private var movieList: List<HeroClass>? = ArrayList()
    private val itemMovieDetails: MutableList<MoviesCardViewItemViewModal> = ArrayList()
    private var images: List<Bitmap>? = null
    fun setMovieData(movieList: List<HeroClass>?, image: List<Bitmap>?) {
        this.movieList = movieList
        images = image
        updateItemsModel()
        notifyDataSetChanged()
    }

    private fun updateItemsModel() {
        itemMovieDetails.clear()
        for (i in movieList!!.indices) {
            itemMovieDetails.add(MoviesCardViewItemViewModal(movieList!![i].name, images!![i], context.resources))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MoviesCardViewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemMovieDetails[position])
    }

    override fun getItemCount(): Int {
        return movieList!!.size
    }

    inner class ViewHolder(private val binding: MoviesCardViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cardViewItemViewModal: MoviesCardViewItemViewModal?) {
            binding.itemViewModal = cardViewItemViewModal
            binding.executePendingBindings()
        }

        init {
            binding.executePendingBindings()
        }
    }

}