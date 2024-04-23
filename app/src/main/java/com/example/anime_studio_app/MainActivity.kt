package com.example.anime_studio_app

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.anime_studio_app.databinding.ActivityMainBinding
import okhttp3.Headers

class anime(title_: String, jap_title_: String, poster_: String, mal_id_: Int) {
    val title = title_
    val jap_title = jap_title_
    val poster = poster_
    val mal_id = mal_id_
}

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // View Binding
    private lateinit var animeList : MutableList<anime> // Anime List
    private lateinit var rvAnime : RecyclerView // Recycler View
    private var epCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Create RecyclerView
        animeList = mutableListOf()
        rvAnime = findViewById(R.id.anime_list)

        // Create Anime List
        for (i in 0 until 15) {
            getAnimeItem()
        }

    }

    private fun getAnimeItem() {
        val client = AsyncHttpClient()
        val params = RequestParams()

        var anime_name = ""
        var anime_image_url = ""
        var anime_jap_name = ""

        client["https://api.jikan.moe/v4/random/anime", params, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {

                // Access a JSON array response with `json.jsonArray`
                // Log.d("DEBUG ARRAY", json.jsonArray.toString())

                // Access a JSON object response with `json.jsonObject`
                // Log.d("anime", json.jsonObject.toString())

                // Get anime data
                val anime_data = json.jsonObject.getJSONObject("data")

                // Get anime name
                anime_name = anime_data.getString("title")

                // Get anime image
                val anime_image_list = anime_data.getJSONObject("images")
                val anime_image_jpg = anime_image_list.getJSONObject("jpg")
                anime_image_url = anime_image_jpg.getString("image_url")

                // Get anime japanese name
                anime_jap_name = anime_data.getString("title_japanese")

                // MAL ID
                val mal_id = anime_data.getString("mal_id").toInt()

                // Get anime episode count
                var anime_epCount_ = anime_data.getString("episodes")
                if (anime_epCount_.equals("null")) {
                    anime_epCount_ = "0"
                }

                val anime_epCount  = anime_epCount_.toInt()


                // Get anime rating
                val anime_rating = anime_data.getString("rating")
                if (
                    (
                            !anime_rating.equals("G - All Ages") &&
                                    !anime_rating.equals("PG - Children") &&
                                    !anime_rating.equals("PG-13 - Teens 13 or older")) ||
                    anime_epCount < 5
                ) {
                    Log.d("anime", "reroll, anime is explicit/no episodes")
                    getAnimeItem()
                }
                else {

                    // Display output in log
                    Log.d("anime", "data = " + anime_data)
                    Log.d("anime", "anime name = " + anime_name)
                    Log.d("anime", "anime image url = " + anime_image_url)
                    Log.d("anime", "anime japanese name = " + anime_jap_name)
                    Log.d("anime", "anime ep count = " + anime_epCount)

                    // Add anime data to the list
                    animeList.add(anime(anime_name, anime_jap_name, anime_image_url, mal_id))
                    Log.d("anime", "anime added " + animeList.size)

                    val adapter = AnimeAdapter(animeList, applicationContext)
                    rvAnime.adapter = adapter
                    rvAnime.layoutManager = LinearLayoutManager(this@MainActivity)

                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                Log.d("anime", "it failed :( " + response + " "+ throwable)
            }
        }]
    }


}