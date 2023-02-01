package kz.jusan.singularityhomeworks.news

import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.jusan.singularityhomeworks.R
import kotlin.math.abs


class FavoritesFragment : Fragment(), MotionLayout.TransitionListener {

    private lateinit var rvNews : RecyclerView
    private lateinit var newsAdapter : NewsAdapter

    private var lastProgress = 0f
    private var fragment: Fragment? = null
    private var last: Float = 0f


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
            fragment = MinPlayerFragment.newInstance().also {
                childFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, it)
                    .commitNow()
            }
        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MotionLayout>(R.id.motionLayout).setTransitionListener(this)

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

    override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
    }

    override fun onTransitionChange(
        p0: MotionLayout?, p1: Int, p2: Int, p3: Float
    ) {
        if (p3 - lastProgress > 0) {
            // from start to end
            val atEnd = abs(p3 - 1f) < 0.1f
            if (atEnd && fragment is MinPlayerFragment) {
                val transaction = requireFragmentManager().beginTransaction()
                transaction
                    .setCustomAnimations(R.animator.show, 0)
                fragment = MaxPlayerFragment.newInstance().also {
                    transaction
                        .setCustomAnimations(R.animator.show, 0)
                        .replace(R.id.fragment_container, it)
                        .commitNow()
                }
            }
        } else {
            // from end to start
            val atStart = p3 < 0.9f
            if (atStart && fragment is MaxPlayerFragment) {
                val transaction = requireFragmentManager().beginTransaction()
                transaction
                    .setCustomAnimations(0, R.animator.hide)
                fragment = MinPlayerFragment.newInstance().also {
                    transaction
                        .replace(R.id.fragment_container, it)
                        .commitNow()
                }
            }
        }
        lastProgress = p3
    }

    override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {

    }

    override fun onTransitionTrigger(
        motionLayout: MotionLayout?,
        triggerId: Int,
        positive: Boolean,
        progress: Float
    ) {

    }

}