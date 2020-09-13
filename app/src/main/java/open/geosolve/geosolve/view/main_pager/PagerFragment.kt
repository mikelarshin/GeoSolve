package open.geosolve.geosolve.view.main_pager

import androidx.activity.addCallback
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_pager.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.MvpFragmentX

class PagerFragment : MvpFragmentX(R.layout.fragment_pager) {

    lateinit var viewPager: ViewPager2
    val pagerAdapter = PagerAdapter()
    val fragments = pagerAdapter.fragments

    override fun setupLayout() {
        viewPager = layout.main_pager

        viewPager.isUserInputEnabled = false
        viewPager.adapter = pagerAdapter
        viewPager.offscreenPageLimit = fragments.size
        layout.bottom_nav_controller.selectedItemId = R.id.calculator
        viewPager.currentItem = 1

        layout.bottom_nav_controller.setOnNavigationItemSelectedListener { menuItem ->
            viewPager.currentItem = getIndexById(menuItem.itemId)
            true
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().findNavController(getNavGraphByIndex(viewPager.currentItem)).popBackStack()
        }
    }

    private fun getIndexById(id: Int) = when(id) {
        R.id.book -> 0
        R.id.calculator -> 1
        else -> null!!
    }

    private fun getNavGraphByIndex(index: Int) = when(index) {
        0 -> R.id.nav_host_book
        1 -> R.id.nav_host_calculator
        else -> null!!
    }
}