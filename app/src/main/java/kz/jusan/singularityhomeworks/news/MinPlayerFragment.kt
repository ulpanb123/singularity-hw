package kz.jusan.singularityhomeworks.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kz.jusan.singularityhomeworks.R

class MinPlayerFragment : Fragment() {
    companion object {
        fun newInstance() = MinPlayerFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_min_player, container, false)
    }
}