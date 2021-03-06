package open.geosolve.geosolve.view.main_pager

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.MvpFragmentX
import open.geosolve.geosolve.view.helpers.inflate

class PagerAdapter : RecyclerView.Adapter<PagerAdapter.PagerHolder>() {

    val fragments = listOf(
        MvpFragmentX(R.layout.host_book_fragment),
        MvpFragmentX(R.layout.host_calclulator_fragment)
    )

    override fun getItemViewType(position: Int): Int = fragments[position].layoutRes

    class PagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, layoutRes: Int) =
        PagerHolder(parent.inflate(layoutRes))

    override fun onBindViewHolder(holder: PagerHolder, position: Int) {}

    override fun getItemCount() = fragments.size
}
