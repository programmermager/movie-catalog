package id.lima.moviecatalog.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.lima.moviecatalog.R
import id.lima.moviecatalog.adapter.DiscoverAdapter
import id.lima.moviecatalog.adapter.GenreAdapter
import id.lima.moviecatalog.base.BaseActivity
import id.lima.moviecatalog.data.Constant
import id.lima.moviecatalog.data.model.objects.GenreModel
import id.lima.moviecatalog.data.model.objects.MovieModel
import id.lima.moviecatalog.data.model.responses.GenreResponse
import id.lima.moviecatalog.data.model.responses.MovieListResponse
import id.lima.moviecatalog.interfaces.DialogClickListener
import id.lima.moviecatalog.ui.detail.DetailActivity
import id.lima.moviecatalog.ui.movie_by_genre.MovieByGenreActivity
import id.lima.moviecatalog.ui.search.SearchActivity
import id.lima.moviecatalog.util.DialogHelper
import id.lima.moviecatalog.util.GlobalHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainContract.View, GenreAdapter.MenuClickListener, DiscoverAdapter.MenuClickListener {
    var presenter: MainContract.Presenter? = null
    var genreAdapter: GenreAdapter? = null
    var nowPlayingAdapter: DiscoverAdapter? = null
    var trendingAdapter: DiscoverAdapter? = null
    var topRatedAdapter: DiscoverAdapter? = null

    var genres: MutableList<GenreModel> = ArrayList()
    var nowPlaying: MutableList<MovieModel> = ArrayList()
    var topRateds: MutableList<MovieModel> = ArrayList()
    var trending: MutableList<MovieModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)

        loadAPIs()

        topRatedAdapter = DiscoverAdapter(topRateds, this)
        rvTopRated.adapter = topRatedAdapter
        rvTopRated.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        trendingAdapter = DiscoverAdapter(trending, this)
        rvTrending.adapter = trendingAdapter
        rvTrending.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)


        nowPlayingAdapter = DiscoverAdapter(nowPlaying, this)
        rvDiscover.adapter = nowPlayingAdapter
        rvDiscover.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        genreAdapter = GenreAdapter(genres, this)
        rvGenre.adapter = genreAdapter
        rvGenre.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        ivSearch.setOnClickListener { startActivity(Intent(this, SearchActivity::class.java)) }
    }

    private fun loadAPIs() {
        presenter?.onGetGenre()
        presenter?.onGetLatest(1)
        presenter?.onGetNowPlaying(1)
        presenter?.onGetTrending(1)
    }

    override fun showReqProccess() {
        GlobalHelper.getInstance().loadLoading(this)
    }

    override fun showGetGenreSuccess(response: GenreResponse) {
        GlobalHelper.getInstance().dissmisLoading()
        genres.addAll(response.genres)
        genreAdapter?.notifyDataSetChanged()
    }

    override fun showGetTrendingSuccess(response: MovieListResponse) {
        GlobalHelper.getInstance().dissmisLoading()
        trending.addAll(response.results)
        trendingAdapter?.notifyDataSetChanged()

    }

    override fun showGetNowPlayingSuccess(response: MovieListResponse) {
        GlobalHelper.getInstance().dissmisLoading()
        nowPlaying.addAll(response.results)
        nowPlayingAdapter?.notifyDataSetChanged()
    }

    override fun showGetLatestSuccess(response: MovieListResponse) {
        GlobalHelper.getInstance().dissmisLoading()
        topRateds.addAll(response.results)
        topRatedAdapter?.notifyDataSetChanged()
    }

    override fun showReqError(code: Int, message: String?) {
        GlobalHelper.getInstance().dissmisLoading()
        DialogHelper.getInstance().showAlertDialog(this, getString(R.string.oops), "$message, ingin mencoba lagi?", getString(R.string.yes), getString(R.string.no), object : DialogClickListener {
            override fun onClickYes() {
                when (code) {
                    Constant.REQ_GENRE -> {
                        presenter?.onGetGenre()
                    }
                    Constant.REQ_NOW_PALYING -> {
                        presenter?.onGetNowPlaying(1)
                    }
                    Constant.REQ_TRENDING -> {
                        presenter?.onGetTrending(1)
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

    override fun onClickMenu(position: Int, data: GenreModel?) {
        startActivity(MovieByGenreActivity.newInstance(this, data?.id!!, data.name!!))
    }

    override fun onClickMenu(position: Int, data: MovieModel?) {
        startActivity(DetailActivity.newInstance(this, data?.id!!))
    }
}