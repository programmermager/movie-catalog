package id.lima.moviecatalog.ui.splash

import android.content.Context
import android.content.Intent
import id.lima.moviecatalog.base.BasePresenter
import id.lima.moviecatalog.base.BaseView

/**
 * Created with love by muhwid on 17/04/20.
 * email muhwid29@gmail.com
 */

interface SplashContract {
    interface View : BaseView<Presenter?> {

    }

    interface Presenter : BasePresenter {
        fun moveToNext(intent: Intent)
    }
}