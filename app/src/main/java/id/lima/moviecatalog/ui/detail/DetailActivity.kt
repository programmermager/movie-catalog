package id.lima.moviecatalog.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.lima.moviecatalog.R
import id.lima.moviecatalog.adapter.CastAdapter
import id.lima.moviecatalog.adapter.ReviewAdapter
import id.lima.moviecatalog.base.BaseActivity
import id.lima.moviecatalog.data.Constant
import id.lima.moviecatalog.data.model.objects.CastModel
import id.lima.moviecatalog.data.model.objects.MovieModel
import id.lima.moviecatalog.data.model.objects.ReviewModel
import id.lima.moviecatalog.data.model.objects.VideoModel
import id.lima.moviecatalog.data.model.responses.CastResponse
import id.lima.moviecatalog.data.model.responses.ReviewResponse
import id.lima.moviecatalog.data.model.responses.VideoResponse
import id.lima.moviecatalog.interfaces.DialogClickListener
import id.lima.moviecatalog.ui.all_review.AllReviewActivity
import id.lima.moviecatalog.util.DialogHelper
import id.lima.moviecatalog.util.GlobalHelper
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.view_empty_review.*

class DetailActivity : BaseActivity(), DetailContract.View {

    companion object {
        fun newInstance(context: Context, id: Int): Intent {
            var i = Intent(context, DetailActivity::class.java)
            i.putExtra("id", id)
            return i
        }
    }

    var castAdapter: CastAdapter? = null
    var reviewAdapter: ReviewAdapter? = null
    var idMovie: Int? = null
    var presenter: DetailContract.Presenter? = null
    var genres = ""
    var cast: MutableList<CastModel> = ArrayList()
    var reviews: MutableList<ReviewModel> = ArrayList()
    var videos: MutableList<VideoModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        idMovie = intent.getIntExtra("id", 0)
        presenter = DetailPresenter(this)

        castAdapter = CastAdapter(cast)
        rvCast.adapter = castAdapter
        rvCast.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)


        reviewAdapter = ReviewAdapter(reviews)
        rvReviews.adapter = reviewAdapter
        rvReviews.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        loadAPIs()
        clickListener()
    }

    private fun clickListener() {
        rlBack.setOnClickListener { finish() }
        tvSeeAll.setOnClickListener {
            startActivity(AllReviewActivity.newInstance(this, idMovie!!))
        }
        ivPlayVideo.setOnClickListener {
            if (videos[0].site!!.toLowerCase() == "youtube") {
                GlobalHelper.getInstance().launchUrl(this, Constant.ytbUrl + videos[0].key)
            }
        }
    }

    private fun loadAPIs() {
        presenter?.onGetDetail(idMovie!!)
        presenter?.onGetReviews(idMovie!!)
        presenter?.onGetCast(idMovie!!)
        presenter?.onGetVideos(idMovie!!)
    }

    override fun showReqProccess() {
        GlobalHelper.getInstance().loadLoading(this)
    }

    override fun showGetMovieSuccess(response: MovieModel) {
        GlobalHelper.getInstance().dissmisLoading()
        Glide.with(this).load(Constant.imgOriginalLarge + response.backdrop_path).into(ivBackdrop)
        Glide.with(this).load(Constant.imgOriginalLarge + response.poster_path).into(ivPoster)
        tvTitle.text = response.title
        tvRate.text = "${response.vote_average}"
        tvPopularity.text = "${response.popularity} Popularity"
        tvRelease.text = "${response.status} (${response.release_date})"
        for (item in response.genres) {
            genres = if (genres == "") item.name!! else genres + " , " + item.name!!
        }
        tvGenre.text = genres
        tvDesc.text = response.overview
    }

    override fun showGetReviewSuccess(response: ReviewResponse) {
        GlobalHelper.getInstance().dissmisLoading()
        reviews.addAll(response.results)
        reviewAdapter?.notifyDataSetChanged()

        llEmptyReview.visibility = if (reviews.size > 0) View.GONE else View.VISIBLE
    }

    override fun showGetVideoSuccess(response: VideoResponse) {
        GlobalHelper.getInstance().dissmisLoading()
        videos.addAll(response.results)
        ivPlayVideo.visibility = if (videos.size > 0) View.VISIBLE else View.GONE
    }

    override fun showGetCastSuccess(response: CastResponse) {
        GlobalHelper.getInstance().dissmisLoading()
        cast.clear()
        cast.addAll(response.cast)
        castAdapter?.notifyDataSetChanged()
    }

    override fun showReqError(code: Int, message: String?) {
        GlobalHelper.getInstance().dissmisLoading()
        DialogHelper.getInstance().showAlertDialog(this, getString(R.string.oops), "$message, ingin mencoba lagi?", getString(R.string.yes), getString(R.string.no), object : DialogClickListener {
            override fun onClickYes() {
                when (code) {
                    Constant.REQ_DETAIL_MOVIE -> {
                        presenter?.onGetDetail(idMovie!!)
                    }
                    Constant.REQ_REVIEWS_MOVIE -> {
                        presenter?.onGetReviews(idMovie!!)
                    }
                    Constant.REQ_CAST -> {
                        presenter?.onGetCast(idMovie!!)
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
}
