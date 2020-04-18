package id.lima.moviecatalog.ui.all_review

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.lima.moviecatalog.R
import id.lima.moviecatalog.adapter.ReviewAdapter
import id.lima.moviecatalog.base.BaseActivity
import id.lima.moviecatalog.data.Constant
import id.lima.moviecatalog.data.model.objects.ReviewModel
import id.lima.moviecatalog.data.model.responses.ReviewResponse
import id.lima.moviecatalog.interfaces.DialogClickListener
import id.lima.moviecatalog.util.DialogHelper
import id.lima.moviecatalog.util.view.EndlessOnScrollListener
import kotlinx.android.synthetic.main.activity_all_review.*
import kotlinx.android.synthetic.main.view_empty_review.*

class AllReviewActivity : BaseActivity(), AllReviewContract.View, SwipeRefreshLayout.OnRefreshListener {

    companion object {
        fun newInstance(context: Context, id: Int): Intent {
            var i = Intent(context, AllReviewActivity::class.java)
            i.putExtra("id", id)
            return i
        }
    }

    var idMovie: Int? = null
    var currentPage = 1
    var totalPages: Int? = null
    var presenter: AllReviewContract.Presenter? = null
    var reviews: MutableList<ReviewModel> = ArrayList()
    var reviewAdapter: ReviewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_review)
        setupToolbar(R.id.toolbar, "All Reviews", R.drawable.ic_arrow_back_orange)
        swipeRefresh.setOnRefreshListener(this)

        idMovie = intent.getIntExtra("id", 0)

        presenter = AllReviewPresenter(this)
        presenter?.onGetReviews(currentPage!!, idMovie!!)

        reviewAdapter = ReviewAdapter(reviews)
        rvReviews.adapter = reviewAdapter
        rvReviews.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        rvReviews.addOnScrollListener(scrollData()!!)

    }

    private fun scrollData(): EndlessOnScrollListener? {
        return object : EndlessOnScrollListener() {
            override fun onLoadMore() {
                if (currentPage < totalPages!!) {
                    currentPage += 1
                    presenter?.onGetReviews(currentPage, idMovie!!)
                }
            }
        }
    }

    override fun showReqProccess() {
        swipeRefresh.isRefreshing = true
    }

    override fun showGetReviewSuccess(response: ReviewResponse) {
        swipeRefresh.isRefreshing = false
        totalPages = response.total_pages
        if (currentPage == 1) {
            reviews.addAll(response.results)
        } else {
            for (item in response.results) {
                reviews.add(item)
            }
        }

        llEmptyReview.visibility = if (reviews.size > 0) View.GONE else View.VISIBLE
        reviewAdapter?.notifyDataSetChanged()

    }

    override fun showReqError(code: Int, message: String?) {
        swipeRefresh.isRefreshing = false
        DialogHelper.getInstance().showAlertDialog(this, getString(R.string.oops), "$message, ingin mencoba lagi?", getString(R.string.yes), getString(R.string.no), object : DialogClickListener {
            override fun onClickYes() {
                when (code) {
                    Constant.REQ_REVIEWS_MOVIE -> {
                        presenter?.onGetReviews(currentPage, idMovie!!)
                    }
                }
            }

            override fun onClickNo() {

            }
        })
    }

    override fun showNetworkNotAvailableError() {
        DialogHelper.getInstance().showAlertDialog(this, getString(R.string.oops), getString(R.string.connection_error), getString(R.string.close), null, object : DialogClickListener {
            override fun onClickYes() {
                finish()
            }

            override fun onClickNo() {

            }
        })
    }

    override fun getContext(): Context {
        return this
    }

    override fun onRefresh() {
        reviews.clear()
        reviewAdapter?.notifyDataSetChanged()
        currentPage = 1
        presenter?.onGetReviews(currentPage, idMovie!!)
    }
}
