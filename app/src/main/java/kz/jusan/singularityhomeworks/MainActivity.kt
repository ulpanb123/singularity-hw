package kz.jusan.singularityhomeworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


class MainActivity : AppCompatActivity(), NewsClickListener {
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNewsListFragment()
    }

    private fun initNewsListFragment() {
        val newsListFragment = NewsListFragment()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fl_list, newsListFragment)
            .commit()
    }

    override fun onNewsClick(news: News) {
        Log.e(TAG, "News details: = $news")

        val newsDetailsFragment = NewsDetailsFragment.newInstance(news)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fl_details, newsDetailsFragment)
            .addToBackStack(null)
            .commit()
    }
}