package id.lima.moviecatalog.ui.movie_by_genre

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import id.lima.moviecatalog.util.view.EndlessOnScrollListener
import kotlinx.android.synthetic.main.activity_movie_by_genre.*

class MovieByGenreActivity : BaseActivity(), MovieByGenreContract.View, SwipeRefreshLayout.OnRefreshListener, MoviesAdapter.MenuClickListener {

    companion object {
        fun newInstance(context: Context, id: Int, title: String): Intent {
            var i = Intent(context, MovieByGenreActivity::class.java)
            i.putExtra("id", id)
            i.putExtra("title", title)
            return i
        }
    }


    var title: String? = null
    var presenter: MovieByGenreContract.Presenter? = null
    var movies: MutableList<MovieModel> = ArrayList()
    var currentPage = 1
    var totalPages = 1
    var adapter: MoviesAdapter? = null
    var genreId: Int = 0

    private fun scrollData(): EndlessOnScrollListener? {
        return object : EndlessOnScrollListener() {
            override fun onLoadMore() {
                if (currentPage < totalPages!!) {
                    currentPage += 1
                    presenter?.onGetMovies(currentPage, genreId)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_by_genre)

        genreId = intent.getIntExtra("id", 0)
        title = intent.getStringExtra("title")

        setupToolbar(R.id.toolbar, "Movie With Genre $title", R.drawable.ic_arrow_back_orange)

        presenter = MovieByGenrePresenter(this)
        presenter?.onGetMovies(currentPage, genreId)

        adapter = MoviesAdapter(movies, this)
        rvMovies.adapter = adapter
        rvMovies.layoutManager = LinearLayoutManager(this)
        rvMovies.addOnScrollListener(scrollData()!!)

        swipeRefresh.setOnRefreshListener(this)
    }

    override fun showReqProccess() {
        swipeRefresh.isRefreshing = true
    }

    override fun showGetMoviesSuccess(response: MovieListResponse) {
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
    }

    override fun showReqError(code: Int, message: String?) {
        swipeRefresh.isRefreshing = false
        DialogHelper.getInstance().showAlertDialog(this, getString(R.string.oops), "$message, ingin mencoba lagi?", getString(R.string.yes), getString(R.string.no), object : DialogClickListener {
            override fun onClickYes() {
                when (code) {
                    Constant.REQ_MOVIE_BY_GENRE -> {
                        presenter?.onGetMovies(currentPage, genreId)
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
        presenter?.onGetMovies(currentPage, genreId)
    }

    override fun onClickMenu(position: Int, data: MovieModel?) {
        startActivity(DetailActivity.newInstance(this, data?.id!!))
    }
}
