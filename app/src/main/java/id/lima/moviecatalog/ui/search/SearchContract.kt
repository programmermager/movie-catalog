package id.lima.moviecatalog.ui.search

import id.lima.moviecatalog.base.BasePresenter
import id.lima.moviecatalog.base.BaseView
import id.lima.moviecatalog.data.model.responses.MovieListResponse

/**
 * Created with love by muhwid on 17/04/20.
 * email muhwid29@gmail.com
 */

interface SearchContract {
    interface View : BaseView<Presenter?> {
        fun showReqProccess()
        fun showSearchMovieSuccess(response: MovieListResponse)
        fun showReqError(code: Int, message: String?)
    }

    interface Presenter : BasePresenter {
        fun onSearchMovie(q: String, page: Int)
    }
}