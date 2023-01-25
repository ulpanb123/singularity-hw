package kz.jusan.singularityhomeworks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.jusan.singularityhomeworks.news.News
import kz.jusan.singularityhomeworks.news.NewsAdapter


class FavoritesFragment : Fragment() {

    private lateinit var rvNews : RecyclerView
    private lateinit var newsAdapter : NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(view)
        loadData()
    }

    private fun initRecyclerView(view : View) {
        newsAdapter = NewsAdapter(layoutInflater)
        val layoutManager = LinearLayoutManager(requireContext())
        rvNews = view.findViewById(R.id.rv_news)

        rvNews.adapter = newsAdapter
        rvNews.layoutManager = layoutManager
    }

    private fun loadData() {
        val news = mutableListOf<News>()

        news.add(News(R.drawable.film1, "Fast & Furious 2009"))
        news.add(News(R.drawable.film2, "Komediia 'Akim' 2020"))
        news.add(News(R.drawable.film1, "Fast & Furious 2009"))
        news.add(News(R.drawable.film2, "Komediia 'Akim' 2020"))

        newsAdapter.updateDataSet(news)
    }

}