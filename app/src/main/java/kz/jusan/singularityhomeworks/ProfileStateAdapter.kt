package kz.jusan.singularityhomeworks

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProfileStateAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProfileMainTabFragment()
            1 -> ProfileStatsTabFragment()
            else -> ProfileUsersTabFragment()
        }
    }

    override fun getItemCount(): Int =
        3
}