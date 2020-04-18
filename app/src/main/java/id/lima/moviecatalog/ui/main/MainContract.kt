package id.lima.moviecatalog.ui.main

import id.lima.moviecatalog.base.BasePresenter
import id.lima.moviecatalog.base.BaseView
import id.lima.moviecatalog.data.model.responses.GenreResponse
import id.lima.moviecatalog.data.model.responses.MovieListResponse

/**
 * Created with love by muhwid on 17/04/20.
 * email muhwid29@gmail.com
 */

interface MainContract {
    interface View : BaseView<Presenter?> {
        fun showReqProccess()
        fun showGetGenreSuccess(response: GenreResponse)
        fun showGetTrendingSuccess(response: MovieListResponse)
        fun showGetNowPlayingSuccess(response: MovieListResponse)
        fun showGetLatestSuccess(response: MovieListResponse)
        fun showReqError(code: Int, message: String?)
    }

    interface Presenter : BasePresenter {
        fun onGetGenre()
        fun onGetNowPlaying(page: Int)
        fun onGetTrending(page: Int)
        fun onGetLatest(page: Int)
    }
}