package id.lima.moviecatalog.data.model.objects

import java.io.Serializable

class CastModel() : Serializable {
    var cast_id: Int = 0
    var gender: Int = 0
    var id: Int = 0
    var character: String? = null
    var name: String? = null
    var profile_path: String? = null
}