package id.lima.moviecatalog.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import id.lima.moviecatalog.R
import id.lima.moviecatalog.base.BaseActivity
import id.lima.moviecatalog.ui.main.MainActivity
import id.lima.moviecatalog.util.GlobalHelper

class SplashActivity : BaseActivity(), SplashContract.View {

    var presenter: SplashContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        presenter?.moveToNext(Intent(context, MainActivity::class.java))
    }

    override fun showNetworkNotAvailableError() {
        GlobalHelper.getInstance().makeToast(getString(R.string.connection_error), this)
    }

    override fun getContext(): Context {
        return this
    }
}