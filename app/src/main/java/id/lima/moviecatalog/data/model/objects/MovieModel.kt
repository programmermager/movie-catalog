package id.lima.moviecatalog.data.model.objects

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class MovieModel() : Serializable, Parcelable {
    var popularity: Double = 0.0
    var vote_count: Int = 0
    var id: Int = 0
    var vote_average: Double = 0.0
    var video: Boolean = false
    var adult: Boolean = false
    var poster_path: String = ""
    var backdrop_path: String = ""
    var original_language: String = ""
    var original_title: String = ""
    var title: String = ""
    var overview: String = ""
    var status: String = ""
    var release_date: String = ""
    var genre_ids: MutableList<Int> = ArrayList()
    var genres: MutableList<GenreModel> = ArrayList()

    constructor(parcel: Parcel) : this() {
        popularity = parcel.readDouble()
        vote_count = parcel.readInt()
        id = parcel.readInt()
        vote_average = parcel.readDouble()
        video = parcel.readByte() != 0.toByte()
        adult = parcel.readByte() != 0.toByte()
        poster_path = parcel.readString()
        backdrop_path = parcel.readString()
        original_language = parcel.readString()
        original_title = parcel.readString()
        title = parcel.readString()
        overview = parcel.readString()
        status = parcel.readString()
        release_date = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(popularity)
        parcel.writeInt(vote_count)
        parcel.writeInt(id)
        parcel.writeDouble(vote_average)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(poster_path)
        parcel.writeString(backdrop_path)
        parcel.writeString(original_language)
        parcel.writeString(original_title)
        parcel.writeString(title)
        parcel.writeString(overview)
        parcel.writeString(status)
        parcel.writeString(release_date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieModel> {
        override fun createFromParcel(parcel: Parcel): MovieModel {
            return MovieModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieModel?> {
            return arrayOfNulls(size)
        }
    }
}