package id.lima.moviecatalog.data.model.responses

import id.lima.moviecatalog.data.model.objects.GenreModel
import java.io.Serializable

/**
 * Created with love by muhwid on 17/04/20.
 * email muhwid29@gmail.com
 */

class GenreResponse : Serializable {
    var genres : MutableList<GenreModel> = ArrayList()
}