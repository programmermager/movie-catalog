package id.lima.moviecatalog.ui.movie_by_genre

import id.lima.moviecatalog.base.BasePresenter
import id.lima.moviecatalog.base.BaseView
import id.lima.moviecatalog.data.model.responses.MovieListResponse

/**
 * Created with love by muhwid on 17/04/20.
 * email muhwid29@gmail.com
 */

interface MovieByGenreContract {
    interface View : BaseView<Presenter?> {
        fun showReqProccess()
        fun showGetMoviesSuccess(response: MovieListResponse)
        fun showReqError(code: Int, message: String?)
    }

    interface Presenter : BasePresenter {
        fun onGetMovies(page : Int, genreId: Int)
    }
}