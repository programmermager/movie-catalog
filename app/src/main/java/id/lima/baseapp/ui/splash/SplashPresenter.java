package id.lima.baseapp.ui.splash;

import java.io.IOException;

import id.lima.baseapp.R;
import id.lima.baseapp.data.Data;
import id.lima.baseapp.util.GlobalHelper;
import id.lima.baseapp.data.model.UserInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created with love by muhwid on 27/10/18
 **/

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View view;
    private Call<UserInfo> call;

    SplashPresenter(SplashContract.View view) {
        this.view = view;
    }

    @Override
    public void onLogin(String username, String password) {

        if (!GlobalHelper.getInstance().isNetworkAvailable(view.getContext())) {
            view.showNetworkNotAvailableError();
            return;
        }
        view.showLoginProgress();
        call = Data.getInstance(view.getContext()).requestLogin(username, password, new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
//                if (response != null && response.isSuccessful()) {
//                    if (response.body()) {
//                        view.showReqLoginSuccess(response.body());
//                    } else
//                        view.showReqLoginError(response.body().message);
//                } else {
//                    try {
//                        view.showReqLoginError(GlobalHelper.getInstance().messageError(response.errorBody().string(), view.getContext()));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                view.showLoginError(view.getContext().getString(R.string.server_error));
            }
        });
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        GlobalHelper.getInstance().cancel(call);
    }
}
