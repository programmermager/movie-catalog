package id.lima.moviecatalog.data.model.responses

import id.lima.moviecatalog.data.model.objects.VideoModel
import java.io.Serializable

/**
 * Created with love by muhwid on 17/04/20.
 * email muhwid29@gmail.com
 */

class VideoResponse : Serializable {
    var results: MutableList<VideoModel> = ArrayList()
}