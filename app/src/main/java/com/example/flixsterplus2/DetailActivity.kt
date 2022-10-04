package com.example.flixsterplus2
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
private const val tag = "DetailActivity"
class DetailActivity : AppCompatActivity() {
    private lateinit var popularityTextView: TextView
    private lateinit var firstAirDateTextView: TextView
    private lateinit var posterImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var overviewTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        firstAirDateTextView = findViewById<TextView>(R.id.FirstAirDate)
        popularityTextView = findViewById<TextView>(R.id.Popularity)
        titleTextView = findViewById<TextView>(R.id.Title)
        overviewTextView = findViewById<TextView>(R.id.Overview)
        posterImageView = findViewById<ImageView>(R.id.Image)
        val tvShow = intent.getSerializableExtra(ExtraTVShow) as TVShow
        firstAirDateTextView.text = tvShow.firstAirDate
        popularityTextView.text = tvShow.popularity
        overviewTextView.text = tvShow.overview
        titleTextView.text = tvShow.name
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + (tvShow.tvShowPosterURL))
            .into(posterImageView)
    }
}