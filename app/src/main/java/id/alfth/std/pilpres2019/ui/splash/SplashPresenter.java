package id.alfth.std.pilpres2019.ui.splash;

import id.alfth.std.pilpres2019.R;
import id.alfth.std.pilpres2019.data.Data;
import id.alfth.std.pilpres2019.data.model.UserInfo;
import id.alfth.std.pilpres2019.util.GlobalHelper;
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
                if (response != null && response.isSuccessful()) {
                    UserInfo userInfo = response.body();
                    if (userInfo != null) {
                        view.showLoginSuccess();
                    } else {
//                        view.showLoginError(response.body().getStatusMessage());
                    }
                } else {
                    view.showLoginError(view.getContext().getString(R.string.server_error));
                }
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
