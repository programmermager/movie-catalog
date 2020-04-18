package id.lima.moviecatalog.ui.detail

import id.lima.moviecatalog.base.BasePresenter
import id.lima.moviecatalog.base.BaseView
import id.lima.moviecatalog.data.model.objects.MovieModel
import id.lima.moviecatalog.data.model.responses.CastResponse
import id.lima.moviecatalog.data.model.responses.ReviewResponse
import id.lima.moviecatalog.data.model.responses.VideoResponse

/**
 * Created with love by muhwid on 17/04/20.
 * email muhwid29@gmail.com
 */

interface DetailContract {
    interface View : BaseView<Presenter?> {
        fun showReqProccess()
        fun showGetMovieSuccess(response: MovieModel)
        fun showGetReviewSuccess(response: ReviewResponse)
        fun showGetVideoSuccess(response: VideoResponse)
        fun showGetCastSuccess(response: CastResponse)
        fun showReqError(code: Int, message: String?)
    }

    interface Presenter : BasePresenter {
        fun onGetDetail(id: Int)
        fun onGetVideos(id: Int)
        fun onGetReviews(id: Int)
        fun onGetCast(id: Int)
    }
}