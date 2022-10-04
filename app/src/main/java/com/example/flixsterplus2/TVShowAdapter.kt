package com.example.flixsterplus2
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import android.content.Context

const val ExtraTVShow = "TVSHOW_EXTRA"
private const val tag = "TVShowAdapter"

class TVShowAdapter(private val context: Context, private val TVShows: List<TVShow>) :
    RecyclerView.Adapter<TVShowAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_tvshow, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val TVShow = TVShows[position]
        holder.bind(TVShow)
    }
    override fun getItemCount() = TVShows.size
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val titleTextView = itemView.findViewById<TextView>(R.id.Title)
        private val overviewTextView = itemView.findViewById<TextView>(R.id.Overview)
        private val firstAirDateTextView = itemView.findViewById<TextView>(R.id.FirstAirDate)
        private val popularityTextView = itemView.findViewById<TextView>(R.id.Popularity)
        private val posterImageView = itemView.findViewById<ImageView>(R.id.Image)
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val TVShow = TVShows[absoluteAdapterPosition]

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("TVSHOW_EXTRA", TVShow)
            context.startActivity(intent)
        }

        fun bind(tvShow: TVShow) {
            firstAirDateTextView.text = tvShow.firstAirDate
            popularityTextView.text = tvShow.popularity
            overviewTextView.text = tvShow.overview
            titleTextView.text = tvShow.name
            Glide.with(context)
                .load(tvShow.tvShowPosterURL)
                .into(posterImageView)
        }
    }
}