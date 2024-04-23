package com.example.anime_studio_app


import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class AnimeAdapter(private val animeList: List<anime>, private var context_: Context) : RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val animeImage: ImageView
        val animeTitle: TextView
        val animeJapName: TextView
        val animeInfo: Button

        init {
            animeImage = view.findViewById(R.id.poster)
            animeTitle = view.findViewById(R.id.title)
            animeJapName = view.findViewById(R.id.jap_name)
            animeInfo = view.findViewById(R.id.anime_info_button)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.anime_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = animeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.d("anime_display", "display: " + animeList[position].title + "\n" + animeList[position].jap_title + "\n" + animeList[position].poster)

        // Anime Image
        Glide.with(holder.itemView)
            .load(animeList[position].poster)
            .fitCenter()
            .into(holder.animeImage)

        holder.animeTitle.text = animeList[position].title  // Anime Title
        holder.animeJapName.text = animeList[position].jap_title  // Anime Episode Count
        holder.animeInfo.setOnClickListener {v ->

            // Go to anime info screen
            val context = v.context // context
            val intent = Intent(context, InfoActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("mal_id", animeList[position].mal_id)
            context.applicationContext.startActivity(intent)
        }
    }
}