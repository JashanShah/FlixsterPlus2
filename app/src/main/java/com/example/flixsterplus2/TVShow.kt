package com.example.flixsterplus2
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import android.support.annotation.Keep
@Keep
@Serializable
class TVShow (
    @SerialName("name")
    val name: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("popularity")
    val popularity: String?,
    @SerialName("first_air_date")
    val firstAirDate: String?,
    @SerialName("poster_path")
    val tvShowPosterURL: String?

) : java.io.Serializable

@Keep
@Serializable
data class SearchTVShowsResults(
    @SerialName("results")
    val results: List<TVShow>?
) : java.io.Serializable{
    val overview = results?.firstOrNull{it.overview != null}?.overview ?: ""
}
@Keep
@Serializable
data class MultiMedia(
    @SerialName("url")
    val url: String?
) : java.io.Serializable