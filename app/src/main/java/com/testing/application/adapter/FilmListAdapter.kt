package com.testing.application.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.testing.application.R
import com.testing.application.util.ImageLoader
import com.testing.application.util.emptyIfNull

interface OnItemClickCallback {

    fun onItemClick(id: Int)
    fun onGenreClick(genre: String)
}

class FilmListAdapter(private val onItemClickCallback: OnItemClickCallback): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val filmList = mutableListOf<FilmModel>()
    var selectedItemPos: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(viewType, parent, false)
        return when(viewType) {
            R.layout.film_item -> FilmListViewHolder(v)
            R.layout.genre_item -> GenreViewHolder(v)
            R.layout.header_item -> HeaderViewHolder(v)
            else -> HeaderViewHolder(v)

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = filmList[position]

        when(holder) {
            is FilmListViewHolder -> holder.bind(item as FilmModel.FilmEntityModel, onItemClickCallback)
            is GenreViewHolder -> holder.bind(item as FilmModel.GenreModel, onItemClickCallback)
            is HeaderViewHolder -> holder.bind(item as FilmModel.HeaderModel)
        }
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    override fun getItemViewType(position: Int) = when(filmList[position]) {
        is FilmModel.FilmEntityModel -> R.layout.film_item
        is FilmModel.GenreModel -> R.layout.genre_item
        is FilmModel.HeaderModel -> R.layout.header_item
    }

    fun updateList(list: List<FilmModel>) {
        this.filmList.clear()
        this.filmList.addAll(list)
        notifyDataSetChanged()
    }

    class FilmListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: FilmModel.FilmEntityModel, onItemClickCallback: OnItemClickCallback) {
            itemView.findViewById<TextView>(R.id.filmText).text = model.filmEntity.localizedName
            val cardViewFilm = itemView.findViewById<CardView>(R.id.cardViewFilm)

            itemView.setOnClickListener {
                onItemClickCallback.onItemClick(model.filmEntity.id)
            }

            val imageView = itemView.findViewById<ImageView>(R.id.filmImageView)
            ImageLoader.loadImage(imageView, model.filmEntity.imageUrl.emptyIfNull())

            cardViewFilm.setOnClickListener {
                onItemClickCallback.onItemClick(model.filmEntity.id)
            }
        }
    }

    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: FilmModel.GenreModel, onItemClickCallback: OnItemClickCallback) {
            itemView.findViewById<TextView>(R.id.filmText).text = model.genre.name
            val cardView = itemView.findViewById<CardView>(R.id.cardView)
            cardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.grey))
            if (selectedItemPos == adapterPosition) { cardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.blue)) }
            cardView.setOnClickListener {
                val lastPos = selectedItemPos
                selectedItemPos = adapterPosition
                notifyItemChanged(lastPos)
                notifyItemChanged(selectedItemPos)
                onItemClickCallback.onGenreClick(model.genre.name)
            }
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: FilmModel.HeaderModel) {
            itemView.findViewById<TextView>(R.id.filmText).text = model.header.name
        }
    }
}