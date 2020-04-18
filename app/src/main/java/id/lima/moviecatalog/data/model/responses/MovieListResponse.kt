package id.lima.moviecatalog.data.model.responses

import id.lima.moviecatalog.data.model.objects.MovieModel
import java.io.Serializable

/**
 * Created with love by muhwid on 17/04/20.
 * email muhwid29@gmail.com
 */

class MovieListResponse : BaseResponse(), Serializable {
    var results: MutableList<MovieModel> = ArrayList()
}