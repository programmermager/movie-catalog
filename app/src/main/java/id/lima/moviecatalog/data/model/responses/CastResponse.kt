package id.lima.moviecatalog.data.model.responses

import id.lima.moviecatalog.data.model.objects.CastModel
import java.io.Serializable

/**
 * Created with love by muhwid on 17/04/20.
 * email muhwid29@gmail.com
 */

class CastResponse : Serializable {
    var cast: MutableList<CastModel> = ArrayList()
}