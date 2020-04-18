package id.lima.moviecatalog.data

interface Constant {
    companion object {
        const val imgOriginalSize = "https://image.tmdb.org/t/p/original"
        const val imgOriginalThumbnail = "https://image.tmdb.org/t/p/w200"
        const val imgOriginalLarge = "https://image.tmdb.org/t/p/w500"
        const val ytbUrl = "https://youtube.com/watch?v="


        const val REQ_GENRE = 1
        const val REQ_TRENDING = 2
        const val REQ_NOW_PALYING = 3
        const val REQ_LATEST = 4
        const val REQ_DETAIL_MOVIE = 5
        const val REQ_REVIEWS_MOVIE = 6
        const val REQ_CAST = 7
        const val REQ_MOVIE_BY_GENRE = 8
        const val REQ_SEARCH = 9
        const val REQ_VIDEO = 10
    }
}