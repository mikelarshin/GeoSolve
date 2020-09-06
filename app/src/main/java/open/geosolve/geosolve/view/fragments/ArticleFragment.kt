package open.geosolve.geosolve.view.fragments

import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_article.view.*
import moxy.ktx.moxyPresenter
import open.geosolve.geosolve.R
import open.geosolve.geosolve.presentation.presenter.ArticleScreenPresenter
import open.geosolve.geosolve.presentation.view.ArticleScreenView
import open.geosolve.geosolve.view.rules.Article
import open.geosolve.geosolve.view.views.recyclers.ArticleRecyclerAdapter

class ArticleFragment : MvpFragmentX(R.layout.fragment_article), ArticleScreenView {

    private val presenter by moxyPresenter { ArticleScreenPresenter() }
    private val adapter = ArticleRecyclerAdapter()
    private lateinit var article: Article

    override fun setupLayout() {
        layout.ruleRecycler.adapter = adapter
        layout.ruleRecycler.layoutManager = LinearLayoutManager(activity)

        article = ArticleFragmentArgs.fromBundle(requireArguments()).article
        adapter.fill(article.articleItems)
    }
}


