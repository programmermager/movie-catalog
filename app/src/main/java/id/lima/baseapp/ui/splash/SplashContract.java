package id.lima.baseapp.ui.splash;

import id.lima.baseapp.base.BasePresenter;
import id.lima.baseapp.base.BaseView;

/**
 * Created with love by muhwid on 27/10/18
 **/

public interface SplashContract {
    interface View extends BaseView<Presenter> {
        void showLoginProgress();

        void showLoginSuccess();

        void showLoginError(String message);
    }

    interface Presenter extends BasePresenter {
        void onLogin(String username, String password);
    }
}
