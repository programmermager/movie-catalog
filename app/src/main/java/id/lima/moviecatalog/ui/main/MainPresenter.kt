package id.lima.moviecatalog.ui.main

import android.util.Log
import id.lima.moviecatalog.R
import id.lima.moviecatalog.data.Constant
import id.lima.moviecatalog.data.Data
import id.lima.moviecatalog.data.model.responses.GenreResponse
import id.lima.moviecatalog.data.model.responses.MovieListResponse
import id.lima.moviecatalog.util.GlobalHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter internal constructor(private val view: MainContract.View) : MainContract.Presenter {

    private var call: Call<GenreResponse>? = null
    private var callDiscover: Call<MovieListResponse>? = null
    private var callTrending: Call<MovieListResponse>? = null
    private var callLatest: Call<MovieListResponse>? = null

    override fun onGetGenre() {
        if (!GlobalHelper.getInstance().isNetworkAvailable(view.context)) {
            view.showNetworkNotAvailableError()
            return
        }
        view.showReqProccess()
        call = Data?.instance!!.reqGenre(object : Callback<GenreResponse?> {
            override fun onResponse(call: Call<GenreResponse?>, response: Response<GenreResponse?>) {
                if (response != null && response.isSuccessful()) {
                    view.showGetGenreSuccess(response.body()!!)
                } else {
                    view.showReqError(Constant.REQ_GENRE, view.context.getString(R.string.connection_error))
                }
            }

            override fun onFailure(call: Call<GenreResponse?>, t: Throwable) {
                view.showReqError(Constant.REQ_GENRE, view.context.getString(R.string.failed_req))
            }
        })
    }

    override fun onGetNowPlaying(page: Int) {
        if (!GlobalHelper.getInstance().isNetworkAvailable(view.context)) {
            view.showNetworkNotAvailableError()
            return
        }
        view.showReqProccess()
        callTrending = Data?.instance!!.reqNowPlaying(page, object : Callback<MovieListResponse?> {
            override fun onResponse(call: Call<MovieListResponse?>, response: Response<MovieListResponse?>) {
                if (response != null && response.isSuccessful()) {
                    view.showGetNowPlayingSuccess(response.body()!!)
                } else {
                    view.showReqError(Constant.REQ_NOW_PALYING, view.context.getString(R.string.connection_error))
                }
            }

            override fun onFailure(call: Call<MovieListResponse?>, t: Throwable) {
                Log.e("ERROR TAG TRENDING", t.message)
                view.showReqError(Constant.REQ_NOW_PALYING, view.context.getString(R.string.failed_req))
            }
        })
    }

    override fun onGetTrending(page: Int) {
        if (!GlobalHelper.getInstance().isNetworkAvailable(view.context)) {
            view.showNetworkNotAvailableError()
            return
        }
        view.showReqProccess()
        callDiscover = Data?.instance!!.reqTrendingMovie(page, object : Callback<MovieListResponse?> {
            override fun onResponse(call: Call<MovieListResponse?>, response: Response<MovieListResponse?>) {
                if (response != null && response.isSuccessful()) {
                    view.showGetTrendingSuccess(response.body()!!)
                } else {
                    view.showReqError(Constant.REQ_TRENDING, view.context.getString(R.string.connection_error))
                }
            }

            override fun onFailure(call: Call<MovieListResponse?>, t: Throwable) {
                Log.e("ERROR TAG", t.message)
                view.showReqError(Constant.REQ_TRENDING, view.context.getString(R.string.failed_req))
            }
        })
    }

    override fun onGetLatest(page: Int) {
        if (!GlobalHelper.getInstance().isNetworkAvailable(view.context)) {
            view.showNetworkNotAvailableError()
            return
        }
        view.showReqProccess()
        callLatest = Data?.instance!!.reqLatestMovies(page, object : Callback<MovieListResponse?> {
            override fun onResponse(call: Call<MovieListResponse?>, response: Response<MovieListResponse?>) {
                if (response != null && response.isSuccessful()) {
                    view.showGetLatestSuccess(response.body()!!)
                } else {
                    view.showReqError(Constant.REQ_LATEST, view.context.getString(R.string.connection_error))
                }
            }

            override fun onFailure(call: Call<MovieListResponse?>, t: Throwable) {
                Log.e("ERROR TAG", t.message)
                view.showReqError(Constant.REQ_LATEST, view.context.getString(R.string.failed_req))
            }
        })
    }

    override fun start() {}

    override fun stop() {
        GlobalHelper.getInstance().cancel(call)
        GlobalHelper.getInstance().cancel(callDiscover)
        GlobalHelper.getInstance().cancel(callTrending)
        GlobalHelper.getInstance().cancel(callLatest)
    }
}