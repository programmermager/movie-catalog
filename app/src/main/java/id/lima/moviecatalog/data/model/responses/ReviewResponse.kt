package id.lima.moviecatalog.data.model.responses

import id.lima.moviecatalog.data.model.objects.ReviewModel
import java.io.Serializable

/**
 * Created with love by muhwid on 17/04/20.
 * email muhwid29@gmail.com
 */

class ReviewResponse : BaseResponse(), Serializable {
    var results: MutableList<ReviewModel> = ArrayList()
}