package id.lima.moviecatalog.data.model.responses

import java.io.Serializable

/**
 * Created with love by muhwid on 17/04/20.
 * email muhwid29@gmail.com
 */

open class BaseResponse : Serializable {
    var page = 0
    var total_pages = 0
    var total_results = 0
}