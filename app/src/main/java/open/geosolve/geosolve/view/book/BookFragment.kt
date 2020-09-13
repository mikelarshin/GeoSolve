package open.geosolve.geosolve.view.book

import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_book.view.*
import open.geosolve.geosolve.R
import open.geosolve.geosolve.view.MvpFragmentX

class BookFragment : MvpFragmentX(R.layout.fragment_book) {

    val bookAdapter = BookRecyclerAdapter()

    override fun setupLayout() {
        layout.articlesRecycler.adapter = bookAdapter
        layout.articlesRecycler.layoutManager = LinearLayoutManager(activity)
    }
}