package com.example.anime_studio_app

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.anime_studio_app.databinding.AnimeInfoBinding
import okhttp3.Headers

class episode(title_: String, jap_title_: String, duration_: String, epNum_ : String) {
    val title = title_
    val jap_title = jap_title_
    val duration = duration_
    val epNum = epNum_
}

class InfoActivity : AppCompatActivity() {

    private lateinit var binding: AnimeInfoBinding // View Binding
    private lateinit var episodeList : MutableList<episode> // Anime List
    private lateinit var rvEpisode : RecyclerView // Recycler View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.anime_info)
        Log.d("hello", "next screen")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.info)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // View Binding
        binding = AnimeInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Get Anime Info [Title, Description]
        val b = intent.extras
        getAnimeItem(b?.getInt("mal_id").toString())

        // Create RecyclerView
        episodeList = mutableListOf()
        rvEpisode = findViewById(R.id.episode_list)
    }

    private fun getAnimeItem(id : String) {
        val client = AsyncHttpClient()
        val params = RequestParams()

        var anime_name = ""
        var anime_image_url = ""
        var anime_jap_name = ""
        var anime_desc = ""

        client["https://api.jikan.moe/v4/anime/$id/full", params, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {

                Log.d("anime", "getting anime info from id")

                // Access a JSON array response with `json.jsonArray`
                // Log.d("DEBUG ARRAY", json.jsonArray.toString())

                // Access a JSON object response with `json.jsonObject`
                 Log.d("anime", json.jsonObject.toString())

                // Get anime data
                val anime_data = json.jsonObject.getJSONObject("data")

                // Get anime name
                anime_name = anime_data.getString("title")

                // Get anime image
                val anime_image_list = anime_data.getJSONObject("images")
                val anime_image_jpg = anime_image_list.getJSONObject("jpg")
                anime_image_url = anime_image_jpg.getString("image_url")

                // Get japanese name
                anime_jap_name = anime_data.getString("title_japanese")

                // Get anime description
                anime_desc = anime_data.getString("synopsis")

                // Empty Description case
                if (anime_desc.equals("null")) {
                    anime_desc = "..."
                }

                // Retrieve Episode List
                getEpisodeItems(id)

                // Put info in the screen
                binding.AnimeName.text = anime_name
                binding.AnimeDesc.text = anime_desc
                binding.AnimeJapName.text = anime_jap_name

                // Add image to screen
                Glide.with(applicationContext)
                    .load(anime_image_url)
                    .centerCrop()
                    .into(binding.AnimePic)



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


    private fun getEpisodeItems(id: String) {
        val client = AsyncHttpClient()
        val params = RequestParams()

        var anime_name = ""
        var anime_image_url = ""
        var anime_jap_name = ""
        var anime_desc = ""

        client["https://api.jikan.moe/v4/anime/$id/episodes", params, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {

                Log.d("anime", "getting anime info from id")

                // Access a JSON array response with `json.jsonArray`
                // Log.d("DEBUG ARRAY", json.jsonArray.toString())

                // Access a JSON object response with `json.jsonObject`
                Log.d("anime", json.jsonObject.toString())

                // Get anime data
                val anime_data = json.jsonObject.getJSONArray("data")
                Log.d("anime", anime_data.toString())
                Log.d("anime", "ep count = " + anime_data.length())


                // For (i in 0 until anime_data.length) {

                    // Get the JSON object
                    // episode_ = anime_data[i]

                    // title

                    // japanese title

                    // episode num

                    // episode duration

                    // val episode_ = episode(title_, jap_title, duration, num)



            //  episodeList.add()
            // }





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
