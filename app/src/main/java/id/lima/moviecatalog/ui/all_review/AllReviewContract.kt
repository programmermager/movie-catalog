package id.lima.moviecatalog.ui.all_review

import id.lima.moviecatalog.base.BasePresenter
import id.lima.moviecatalog.base.BaseView
import id.lima.moviecatalog.data.model.responses.ReviewResponse

/**
 * Created with love by muhwid on 17/04/20.
 * email muhwid29@gmail.com
 */

interface AllReviewContract {
    interface View : BaseView<Presenter?> {
        fun showReqProccess()
        fun showGetReviewSuccess(response: ReviewResponse)
        fun showReqError(code: Int, message: String?)
    }

    interface Presenter : BasePresenter {
        fun onGetReviews(page: Int, id: Int)
    }
}