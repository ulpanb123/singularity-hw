package kz.jusan.singularityhomeworks.news

import android.app.ProgressDialog.show
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.jusan.singularityhomeworks.R
import kotlin.math.abs


class FavoritesFragment : Fragment() {

    private lateinit var rvNews : RecyclerView
    private lateinit var newsAdapter : NewsAdapter

    private var fragment: Fragment? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            fragment = MaxPlayerFragment.newInstance().also {
                childFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, it)
                    .commitNow()
            }
        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.btn_close).setOnClickListener {
            view.findViewById<FrameLayout>(R.id.fragment_container).visibility = View.GONE
            Log.e("NewsFragment", "button clicked")
        }
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