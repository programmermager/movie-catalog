package id.lima.moviecatalog.ui.search

import id.lima.moviecatalog.R
import id.lima.moviecatalog.data.Constant
import id.lima.moviecatalog.data.Data
import id.lima.moviecatalog.data.model.responses.MovieListResponse
import id.lima.moviecatalog.util.GlobalHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPresenter internal constructor(private val view: SearchContract.View) : SearchContract.Presenter {

    private var call: Call<MovieListResponse>? = null

    override fun onSearchMovie(q: String, page: Int) {
        if (!GlobalHelper.getInstance().isNetworkAvailable(view.context)) {
            view.showNetworkNotAvailableError()
            return
        }
        view.showReqProccess()
        call = Data?.instance!!.reqSearchMovie(page, q, object : Callback<MovieListResponse?> {
            override fun onResponse(call: Call<MovieListResponse?>, response: Response<MovieListResponse?>) {
                if (response != null && response.isSuccessful) {
                    view.showSearchMovieSuccess(response.body()!!)
                } else {
                    view.showReqError(Constant.REQ_SEARCH, view.context.getString(R.string.connection_error))
                }
            }

            override fun onFailure(call: Call<MovieListResponse?>, t: Throwable) {
                view.showReqError(Constant.REQ_SEARCH, view.context.getString(R.string.failed_req))
            }
        })
    }

    override fun start() {}

    override fun stop() {
        GlobalHelper.getInstance().cancel(call)
    }
}