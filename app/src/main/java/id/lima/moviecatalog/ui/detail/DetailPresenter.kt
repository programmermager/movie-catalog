package id.lima.moviecatalog.ui.detail

import android.util.Log
import id.lima.moviecatalog.R
import id.lima.moviecatalog.data.Constant
import id.lima.moviecatalog.data.Data
import id.lima.moviecatalog.data.model.objects.MovieModel
import id.lima.moviecatalog.data.model.responses.CastResponse
import id.lima.moviecatalog.data.model.responses.ReviewResponse
import id.lima.moviecatalog.data.model.responses.VideoResponse
import id.lima.moviecatalog.util.GlobalHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter internal constructor(private val view: DetailContract.View) : DetailContract.Presenter {

    private var call: Call<ReviewResponse>? = null
    private var callDetail: Call<MovieModel>? = null
    private var callCast: Call<CastResponse>? = null
    private var callVideo: Call<VideoResponse>? = null

    override fun onGetDetail(id: Int) {
        if (!GlobalHelper.getInstance().isNetworkAvailable(view.context)) {
            view.showNetworkNotAvailableError()
            return
        }
        view.showReqProccess()
        callDetail = Data?.instance!!.reqDetailMovie(id, object : Callback<MovieModel?> {
            override fun onResponse(call: Call<MovieModel?>, response: Response<MovieModel?>) {
                if (response != null && response.isSuccessful()) {
                    view.showGetMovieSuccess(response.body()!!)
                } else {
                    view.showReqError(Constant.REQ_DETAIL_MOVIE, view.context.getString(R.string.connection_error))
                }
            }

            override fun onFailure(call: Call<MovieModel?>, t: Throwable) {
                Log.e("ERROR TAG", t.message)
                view.showReqError(Constant.REQ_DETAIL_MOVIE, view.context.getString(R.string.failed_req))
            }
        })
    }

    override fun onGetVideos(id: Int) {
        if (!GlobalHelper.getInstance().isNetworkAvailable(view.context)) {
            view.showNetworkNotAvailableError()
            return
        }
        view.showReqProccess()
        callVideo = Data?.instance!!.reqVideoMovie(id, object : Callback<VideoResponse?> {
            override fun onResponse(call: Call<VideoResponse?>, response: Response<VideoResponse?>) {
                if (response != null && response.isSuccessful) {
                    view.showGetVideoSuccess(response.body()!!)
                } else {
                    view.showReqError(Constant.REQ_VIDEO, view.context.getString(R.string.connection_error))
                }
            }

            override fun onFailure(call: Call<VideoResponse?>, t: Throwable) {
                view.showReqError(Constant.REQ_VIDEO, view.context.getString(R.string.failed_req))
            }
        })
    }

    override fun onGetReviews(id: Int) {
        if (!GlobalHelper.getInstance().isNetworkAvailable(view.context)) {
            view.showNetworkNotAvailableError()
            return
        }
        view.showReqProccess()
        call = Data?.instance!!.reqReviewMovie(1, id, object : Callback<ReviewResponse?> {
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

    override fun onGetCast(id: Int) {
        if (!GlobalHelper.getInstance().isNetworkAvailable(view.context)) {
            view.showNetworkNotAvailableError()
            return
        }
        view.showReqProccess()
        callCast = Data?.instance!!.reqCastMovie(id, object : Callback<CastResponse?> {
            override fun onResponse(call: Call<CastResponse?>, response: Response<CastResponse?>) {
                if (response != null && response.isSuccessful()) {
                    view.showGetCastSuccess(response.body()!!)
                } else {
                    view.showReqError(Constant.REQ_CAST, view.context.getString(R.string.connection_error))
                }
            }

            override fun onFailure(call: Call<CastResponse?>, t: Throwable) {
                Log.e("ERROR TAG", t.message)
                view.showReqError(Constant.REQ_CAST, view.context.getString(R.string.failed_req))
            }
        })
    }

    override fun start() {}

    override fun stop() {
        GlobalHelper.getInstance().cancel(call)
        GlobalHelper.getInstance().cancel(callDetail)
        GlobalHelper.getInstance().cancel(callCast)
        GlobalHelper.getInstance().cancel(callVideo)
    }
}