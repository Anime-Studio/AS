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
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    //private val title = mutableListOf<String>()
    //private val epCount = mutableListOf<String>()
    //private val poster = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        //recyclerView.layoutManager = LinearLayoutManager(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val client = AsyncHttpClient()
        val params = RequestParams()
        params["limit"] = "5"
        params["page"] = "0"
        client["https://api.jikan.moe/v4/random/anime", params, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                // Access a JSON array response with `json.jsonArray`
                //Log.d("DEBUG ARRAY", "hello?")
                //Log.d("DEBUG ARRAY", json.jsonArray.toString())
                // Access a JSON object response with `json.jsonObject`
                Log.d("anime", json.jsonObject.toString())

                // Get anime data
                val anime_data = json.jsonObject.getJSONObject("data")

                // Get anime name
                val anime_name = anime_data.getString("title")

                // Get anime image
                val anime_image_list = anime_data.getJSONObject("images")
                val anime_image_jpg = anime_image_list.getJSONObject("jpg")
                val anime_image_url = anime_image_jpg.getString("image_url")

                // Get anime episode count
                val anime_ep_count = anime_data.getString("episodes")

                // Display output in log
                Log.d("anime", "data = " + anime_data)
                Log.d("anime", "anime name = " + anime_name)
                Log.d("anime", "anime image url = " + anime_image_url)
                Log.d("anime", "anime ep count = " + anime_ep_count)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                Log.d("DEBUG ARRAY", "it failed :( " + response + " "+ throwable)
            }
        }]

    }
}