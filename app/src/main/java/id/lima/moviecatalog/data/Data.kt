package id.lima.moviecatalog.data

import com.google.gson.Gson
import id.lima.moviecatalog.BuildConfig
import id.lima.moviecatalog.data.model.objects.MovieModel
import id.lima.moviecatalog.data.model.responses.*
import id.lima.moviecatalog.data.remote.RemoteRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by nawin on 6/13/17.
 */
class Data private constructor(private val remoteRepo: RemoteRepo) {
    companion object {
        private var data: Data? = null

        val instance: Data?
            get() {
                if (data == null) {
                    val remoteRepo = Retrofit.Builder()
                            .baseUrl(DataModule.BASE_URL)
                            .client(DataModule.getHttpClient()) //getHttpClient is for logging the request and response
                            .addConverterFactory(GsonConverterFactory.create(Gson()))
                            .build().create(RemoteRepo::class.java)
                    data = Data(remoteRepo)
                }
                return data
            }
    }


    fun reqGenre(callback: Callback<GenreResponse?>): Call<GenreResponse> {
        val call = remoteRepo.reqGenre(BuildConfig.API_KEY)
        call.enqueue(object : Callback<GenreResponse?> {
            override fun onResponse(call: Call<GenreResponse?>, response: Response<GenreResponse?>) {
                if (response != null && response.isSuccessful) {
                    callback.onResponse(call, response)
                } else {
                    if (response.code() >= 400) {
                        callback.onResponse(call, response)
                        return
                    }
                    callback.onFailure(call, Exception())
                }
            }

            override fun onFailure(call: Call<GenreResponse?>, t: Throwable) {
                if (!call.isCanceled) callback.onFailure(call, t)
            }
        })
        return call
    }

    fun reqNowPlaying(page: Int, callback: Callback<MovieListResponse?>): Call<MovieListResponse> {
        val call = remoteRepo.reqNowPlaying(page, BuildConfig.API_KEY)
        call.enqueue(object : Callback<MovieListResponse?> {
            override fun onResponse(call: Call<MovieListResponse?>, response: Response<MovieListResponse?>) {
                if (response != null && response.isSuccessful) {
                    callback.onResponse(call, response)
                } else {
                    if (response.code() >= 400) {
                        callback.onResponse(call, response)
                        return
                    }
                    callback.onFailure(call, Exception())
                }
            }

            override fun onFailure(call: Call<MovieListResponse?>, t: Throwable) {
                if (!call.isCanceled) callback.onFailure(call, t)
            }
        })
        return call
    }

    fun reqLatestMovies(page: Int, callback: Callback<MovieListResponse?>): Call<MovieListResponse> {
        val call = remoteRepo.reqLatestMovies(page, BuildConfig.API_KEY)
        call.enqueue(object : Callback<MovieListResponse?> {
            override fun onResponse(call: Call<MovieListResponse?>, response: Response<MovieListResponse?>) {
                if (response != null && response.isSuccessful) {
                    callback.onResponse(call, response)
                } else {
                    if (response.code() >= 400) {
                        callback.onResponse(call, response)
                        return
                    }
                    callback.onFailure(call, Exception())
                }
            }

            override fun onFailure(call: Call<MovieListResponse?>, t: Throwable) {
                if (!call.isCanceled) callback.onFailure(call, t)
            }
        })
        return call
    }


    fun reqTrendingMovie(page: Int, callback: Callback<MovieListResponse?>): Call<MovieListResponse> {
        val call = remoteRepo.reqTrendingMovie(page, BuildConfig.API_KEY)
        call.enqueue(object : Callback<MovieListResponse?> {
            override fun onResponse(call: Call<MovieListResponse?>, response: Response<MovieListResponse?>) {
                if (response != null && response.isSuccessful) {
                    callback.onResponse(call, response)
                } else {
                    if (response.code() >= 400) {
                        callback.onResponse(call, response)
                        return
                    }
                    callback.onFailure(call, Exception())
                }
            }

            override fun onFailure(call: Call<MovieListResponse?>, t: Throwable) {
                if (!call.isCanceled) callback.onFailure(call, t)
            }
        })
        return call
    }

    fun reqDetailMovie(id: Int, callback: Callback<MovieModel?>): Call<MovieModel> {
        val call = remoteRepo.reqDetailMovie(id, BuildConfig.API_KEY)
        call.enqueue(object : Callback<MovieModel?> {
            override fun onResponse(call: Call<MovieModel?>, response: Response<MovieModel?>) {
                if (response != null && response.isSuccessful) {
                    callback.onResponse(call, response)
                } else {
                    if (response.code() >= 400) {
                        callback.onResponse(call, response)
                        return
                    }
                    callback.onFailure(call, Exception())
                }
            }

            override fun onFailure(call: Call<MovieModel?>, t: Throwable) {
                if (!call.isCanceled) callback.onFailure(call, t)
            }
        })
        return call
    }

    fun reqReviewMovie(page: Int, id: Int, callback: Callback<ReviewResponse?>): Call<ReviewResponse> {
        val call = remoteRepo.reqReviewByMovie(id, page, BuildConfig.API_KEY)
        call.enqueue(object : Callback<ReviewResponse?> {
            override fun onResponse(call: Call<ReviewResponse?>, response: Response<ReviewResponse?>) {
                if (response != null && response.isSuccessful) {
                    callback.onResponse(call, response)
                } else {
                    if (response.code() >= 400) {
                        callback.onResponse(call, response)
                        return
                    }
                    callback.onFailure(call, Exception())
                }
            }

            override fun onFailure(call: Call<ReviewResponse?>, t: Throwable) {
                if (!call.isCanceled) callback.onFailure(call, t)
            }
        })
        return call
    }

    fun reqVideoMovie(id: Int, callback: Callback<VideoResponse?>): Call<VideoResponse> {
        val call = remoteRepo.reqMovieVideo(id, BuildConfig.API_KEY)
        call.enqueue(object : Callback<VideoResponse?> {
            override fun onResponse(call: Call<VideoResponse?>, response: Response<VideoResponse?>) {
                if (response != null && response.isSuccessful) {
                    callback.onResponse(call, response)
                } else {
                    if (response.code() >= 400) {
                        callback.onResponse(call, response)
                        return
                    }
                    callback.onFailure(call, Exception())
                }
            }

            override fun onFailure(call: Call<VideoResponse?>, t: Throwable) {
                if (!call.isCanceled) callback.onFailure(call, t)
            }
        })
        return call
    }

    fun reqCastMovie(id: Int, callback: Callback<CastResponse?>): Call<CastResponse> {
        val call = remoteRepo.reqCastByMovie(id, BuildConfig.API_KEY)
        call.enqueue(object : Callback<CastResponse?> {
            override fun onResponse(call: Call<CastResponse?>, response: Response<CastResponse?>) {
                if (response != null && response.isSuccessful) {
                    callback.onResponse(call, response)
                } else {
                    if (response.code() >= 400) {
                        callback.onResponse(call, response)
                        return
                    }
                    callback.onFailure(call, Exception())
                }
            }

            override fun onFailure(call: Call<CastResponse?>, t: Throwable) {
                if (!call.isCanceled) callback.onFailure(call, t)
            }
        })
        return call
    }

    fun reqMovieByGenre(page: Int, id: Int, callback: Callback<MovieListResponse?>): Call<MovieListResponse> {
        val call = remoteRepo.reqMovieByGenre(page, BuildConfig.API_KEY, id)
        call.enqueue(object : Callback<MovieListResponse?> {
            override fun onResponse(call: Call<MovieListResponse?>, response: Response<MovieListResponse?>) {
                if (response != null && response.isSuccessful) {
                    callback.onResponse(call, response)
                } else {
                    if (response.code() >= 400) {
                        callback.onResponse(call, response)
                        return
                    }
                    callback.onFailure(call, Exception())
                }
            }

            override fun onFailure(call: Call<MovieListResponse?>, t: Throwable) {
                if (!call.isCanceled) callback.onFailure(call, t)
            }
        })
        return call
    }

    fun reqSearchMovie(page: Int, query: String, callback: Callback<MovieListResponse?>): Call<MovieListResponse> {
        val call = remoteRepo.reqSearchMovie(page, query, BuildConfig.API_KEY)
        call.enqueue(object : Callback<MovieListResponse?> {
            override fun onResponse(call: Call<MovieListResponse?>, response: Response<MovieListResponse?>) {
                if (response != null && response.isSuccessful) {
                    callback.onResponse(call, response)
                } else {
                    if (response.code() >= 400) {
                        callback.onResponse(call, response)
                        return
                    }
                    callback.onFailure(call, Exception())
                }
            }

            override fun onFailure(call: Call<MovieListResponse?>, t: Throwable) {
                if (!call.isCanceled) callback.onFailure(call, t)
            }
        })
        return call
    }


}