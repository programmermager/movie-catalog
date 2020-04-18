package id.lima.moviecatalog.ui.splash

import android.content.Intent
import id.lima.moviecatalog.ui.splash.SplashContract.Presenter

/**
 * Created with love by muhwid on 27/10/18
 */
class SplashPresenter internal constructor(private val view: SplashContract.View) : Presenter {

    override fun moveToNext(intent: Intent) {
        view.context.startActivity(intent)
    }

    override fun start() {}

    override fun stop() {
    }
}