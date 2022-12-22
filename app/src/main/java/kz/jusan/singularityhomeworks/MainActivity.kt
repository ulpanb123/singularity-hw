package kz.jusan.singularityhomeworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView


class MainActivity : AppCompatActivity(), NewsClickListener {
    val TAG = "MainActivity"

    private val list = mutableListOf<News>()
    private var currIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()
        initNewsListFragment()
        initNavButtons()
    }

    private fun getNews() {
        list.add(News(title = "News 1", author = "Author 1", date = "Date 1"))
        list.add(News(title = "News 2", author = "Author 2", date = "Date 2"))
        list.add(News(title = "News 3", author = "Author 3", date = "Date 3"))
        list.add(News(title = "News 4", author = "Author 4", date = "Date 4"))
        list.add(News(title = "News 5", author = "Author 5", date = "Date 5"))
        list.add(News(title = "News 6", author = "Author 6", date = "Date 6"))
    }

    private fun initNewsListFragment() {
        val newsListFragment = NewsListFragment()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fl_list, newsListFragment)
            .commit()
    }

    override fun onNewsClick(index: Int) {
        currIndex = index
        val news =list[currIndex]
        openNewsDetails(news)
    }

    private fun openNewsDetails(news: News) {
        val newsDetailsFragment = NewsDetailsFragment.newInstance(news)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fl_details, newsDetailsFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initNavButtons() {
        val tvBack: TextView = findViewById(R.id.tv_back)
        tvBack.setOnClickListener {
            currIndex -= 1
            if (currIndex < 0) {
                currIndex = 0
            }
            val news = list[currIndex]
            openNewsDetails(news)
        }

        val tvForward: TextView = findViewById(R.id.tv_forward)
        tvForward.setOnClickListener {
            currIndex += 1
            if (currIndex > 5) {
                currIndex = 5
            }
            val news = list[currIndex]
            openNewsDetails(news)
        }
    }
}