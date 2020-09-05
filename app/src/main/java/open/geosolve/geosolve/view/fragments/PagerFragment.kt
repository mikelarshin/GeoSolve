package open.geosolve.geosolve.view.fragments

import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_pager.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.views.pager.PagerAdapter


class PagerFragment : MvpFragmentX(R.layout.fragment_pager) {

    lateinit var viewPager: ViewPager2

    override fun setupLayout() {
        viewPager = layout.main_pager

        viewPager.isUserInputEnabled = false
        viewPager.adapter = PagerAdapter()
        layout.bottom_nav_controller.selectedItemId = R.id.calculator
        viewPager.currentItem = 1

        layout.bottom_nav_controller.setOnNavigationItemSelectedListener { menuItem ->
            viewPager.currentItem = when(menuItem.itemId) {
                R.id.book -> 0
                R.id.calculator -> 1
                else -> null!!
            }
            true
        }
    }
}