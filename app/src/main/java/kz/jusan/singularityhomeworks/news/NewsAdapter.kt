package kz.jusan.singularityhomeworks.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.jusan.singularityhomeworks.R

class NewsAdapter(private val layoutInflator : LayoutInflater) :
    RecyclerView.Adapter<NewsViewholder>() {

    private val news : MutableList<News> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewholder {
        val view = layoutInflator.inflate(R.layout.item_video_player, parent, false)
        return NewsViewholder(view)
    }

    override fun onBindViewHolder(holder: NewsViewholder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }

    fun updateDataSet(newData : List<News>) {
        news.clear()
        news.addAll(newData)

        notifyDataSetChanged()
    }
}

class NewsViewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val newsImage : ImageView = itemView.findViewById(R.id.iv_news)
    private val newsTitle : TextView = itemView.findViewById(R.id.tv_news_name)

    fun bind(news : News) {
        newsImage.setImageResource(news.image)
        newsTitle.text = news.title
    }
}