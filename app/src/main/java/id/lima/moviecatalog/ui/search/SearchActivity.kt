package id.lima.moviecatalog.ui.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.lima.moviecatalog.R
import id.lima.moviecatalog.adapter.MoviesAdapter
import id.lima.moviecatalog.base.BaseActivity
import id.lima.moviecatalog.data.Constant
import id.lima.moviecatalog.data.model.objects.MovieModel
import id.lima.moviecatalog.data.model.responses.MovieListResponse
import id.lima.moviecatalog.interfaces.DialogClickListener
import id.lima.moviecatalog.ui.detail.DetailActivity
import id.lima.moviecatalog.util.DialogHelper
import id.lima.moviecatalog.util.GlobalHelper
import id.lima.moviecatalog.util.view.EndlessOnScrollListener
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.view_empty_movies.*


class SearchActivity : BaseActivity(), SearchContract.View, SwipeRefreshLayout.OnRefreshListener, MoviesAdapter.MenuClickListener {

    var presenter: SearchContract.Presenter? = null
    var movies: MutableList<MovieModel> = ArrayList()
    var currentPage = 1
    var totalPages = 1
    var adapter: MoviesAdapter? = null

    private fun scrollData(): EndlessOnScrollListener? {
        return object : EndlessOnScrollListener() {
            override fun onLoadMore() {
                if (currentPage < totalPages!!) {
                    currentPage += 1
                    presenter?.onSearchMovie(etSearch.text.toString(), currentPage)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        presenter = SearchPresenter(this)

        adapter = MoviesAdapter(movies, this)
        rvMovies.adapter = adapter
        rvMovies.layoutManager = LinearLayoutManager(this)
        rvMovies.addOnScrollListener(scrollData()!!)

        llEmptyMovie.visibility = View.VISIBLE

        etSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (etSearch.text.toString().isEmpty()) {
                    GlobalHelper.getInstance().makeToast("Search Query Cannot be empty", this)
                    return@OnEditorActionListener true
                }
                presenter?.onSearchMovie(etSearch.text.toString(), currentPage)
                return@OnEditorActionListener true
            }
            false
        })

        swipeRefresh.setOnRefreshListener(this)
    }

    override fun showReqProccess() {
        swipeRefresh.isRefreshing = true
    }

    override fun showSearchMovieSuccess(response: MovieListResponse) {
        swipeRefresh.isRefreshing = false
        totalPages = response.total_pages
        if (currentPage == 1) {
            movies.addAll(response.results)
        } else {
            for (item in response.results) {
                movies.add(item)
            }
        }

        adapter?.notifyDataSetChanged()
        llEmptyMovie.visibility = if (movies.size > 0) View.GONE else View.VISIBLE
    }


    override fun showReqError(code: Int, message: String?) {
        swipeRefresh.isRefreshing = false
        DialogHelper.getInstance().showAlertDialog(this, getString(R.string.oops), "$message, ingin mencoba lagi?", getString(R.string.yes), getString(R.string.no), object : DialogClickListener {
            override fun onClickYes() {
                when (code) {
                    Constant.REQ_SEARCH -> {
                        presenter?.onSearchMovie(etSearch.text.toString(), currentPage)
                    }
                }
            }

            override fun onClickNo() {

            }
        })
    }

    override fun showNetworkNotAvailableError() {
        DialogHelper.getInstance().showAlertDialog(this, getString(R.string.oops), getString(R.string.connection_error), getString(R.string.close), null, object : DialogClickListener {
            override fun onClickYes() {
                finish()
            }

            override fun onClickNo() {

            }
        })
    }

    override fun getContext(): Context {
        return this
    }

    override fun onRefresh() {
        movies.clear()
        adapter?.notifyDataSetChanged()
        currentPage = 1
        totalPages = 1
        presenter?.onSearchMovie(etSearch.text.toString(), currentPage)
    }

    override fun onClickMenu(position: Int, data: MovieModel?) {
        startActivity(DetailActivity.newInstance(this, data?.id!!))
    }
}
