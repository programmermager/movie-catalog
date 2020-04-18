package id.lima.moviecatalog.ui.movie_by_genre

import id.lima.moviecatalog.R
import id.lima.moviecatalog.data.Constant
import id.lima.moviecatalog.data.Data
import id.lima.moviecatalog.data.model.responses.MovieListResponse
import id.lima.moviecatalog.util.GlobalHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieByGenrePresenter internal constructor(private val view: MovieByGenreContract.View) : MovieByGenreContract.Presenter {

    private var call: Call<MovieListResponse>? = null

    override fun onGetMovies(page: Int, genreId: Int) {
        if (!GlobalHelper.getInstance().isNetworkAvailable(view.context)) {
            view.showNetworkNotAvailableError()
            return
        }
        view.showReqProccess()
        call = Data?.instance!!.reqMovieByGenre(page, genreId, object : Callback<MovieListResponse?> {
            override fun onResponse(call: Call<MovieListResponse?>, response: Response<MovieListResponse?>) {
                if (response != null && response.isSuccessful) {
                    view.showGetMoviesSuccess(response.body()!!)
                } else {
                    view.showReqError(Constant.REQ_MOVIE_BY_GENRE, view.context.getString(R.string.connection_error))
                }
            }

            override fun onFailure(call: Call<MovieListResponse?>, t: Throwable) {
                view.showReqError(Constant.REQ_MOVIE_BY_GENRE, view.context.getString(R.string.failed_req))
            }
        })
    }

    override fun start() {}

    override fun stop() {
        GlobalHelper.getInstance().cancel(call)
    }
}