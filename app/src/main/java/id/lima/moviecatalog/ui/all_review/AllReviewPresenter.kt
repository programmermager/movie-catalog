package id.lima.moviecatalog.ui.all_review

import android.util.Log
import id.lima.moviecatalog.R
import id.lima.moviecatalog.data.Constant
import id.lima.moviecatalog.data.Data
import id.lima.moviecatalog.data.model.responses.ReviewResponse
import id.lima.moviecatalog.util.GlobalHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllReviewPresenter internal constructor(private val view: AllReviewContract.View) : AllReviewContract.Presenter {

    private var call: Call<ReviewResponse>? = null

    override fun onGetReviews(page: Int, id: Int) {
        if (!GlobalHelper.getInstance().isNetworkAvailable(view.context)) {
            view.showNetworkNotAvailableError()
            return
        }
        view.showReqProccess()
        call = Data?.instance!!.reqReviewMovie(page, id, object : Callback<ReviewResponse?> {
            override fun onResponse(call: Call<ReviewResponse?>, response: Response<ReviewResponse?>) {
                if (response != null && response.isSuccessful()) {
                    view.showGetReviewSuccess(response.body()!!)
                } else {
                    view.showReqError(Constant.REQ_REVIEWS_MOVIE, view.context.getString(R.string.connection_error))
                }
            }

            override fun onFailure(call: Call<ReviewResponse?>, t: Throwable) {
                Log.e("ERROR TAG", t.message)
                view.showReqError(Constant.REQ_REVIEWS_MOVIE, view.context.getString(R.string.failed_req))
            }
        })
    }

    override fun start() {}

    override fun stop() {
        GlobalHelper.getInstance().cancel(call)
    }
}