package com.example.flixsterplus2
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.flixsterplus2.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}
private const val tag = "MainActivity/"
private const val SEARCH_API_KEY = BuildConfig.API_KEY
private const val TVSHOW_SEARCH_URL =
    "https://api.themoviedb.org/3/trending/tv/day?api_key=${SEARCH_API_KEY}&language=en-US&page=1"
class MainActivity : AppCompatActivity() {
    private val tvShows = mutableListOf<TVShow>()
    private lateinit var tvShowsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvShowsRecyclerView = findViewById(R.id.TVShowsRecyclerView)
        val tvShowAdapter = TVShowAdapter(this, tvShows)
        tvShowsRecyclerView.adapter = tvShowAdapter
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        tvShowsRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            tvShowsRecyclerView.addItemDecoration(dividerItemDecoration)
        }
        val client = AsyncHttpClient()
        client.get(TVSHOW_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                results: String?,
                throwable: Throwable?
            ) {
                Log.e(tag, "Failed to fetch TV shows: $statusCode")
            }
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(tag, "Successfully fetched TV shows: $json")
                try {
                    val parsedJson = createJson().decodeFromString(
                        SearchTVShowsResults.serializer(),
                        json.jsonObject.toString()
                    )

                    parsedJson.results?.let { list ->
                        tvShows.addAll(list)

                        tvShowAdapter.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    Log.e(tag, "Exception: $e")
                }
            }

        })

    }
}