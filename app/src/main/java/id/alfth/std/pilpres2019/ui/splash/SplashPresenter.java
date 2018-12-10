package id.alfth.std.pilpres2019.ui.splash;

import id.alfth.std.pilpres2019.R;
import id.alfth.std.pilpres2019.data.Data;
import id.alfth.std.pilpres2019.data.model.UserInfo;
import id.alfth.std.pilpres2019.data.model.api.BaseResponse;
import id.alfth.std.pilpres2019.util.GlobalHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created with love by muhwid on 27/10/18
 **/

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View view;
    private Call<BaseResponse<UserInfo>> call;

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
        call = Data.getInstance(view.getContext()).requestLogin(username, password, new Callback<BaseResponse<UserInfo>>() {
            @Override
            public void onResponse(Call<BaseResponse<UserInfo>> call, Response<BaseResponse<UserInfo>> response) {
                if (response != null && response.isSuccessful()) {
                    UserInfo userInfo = response.body().getResponse();
                    if (userInfo != null) {
                        view.showLoginSuccess();
                    } else {
                        view.showLoginError(response.body().getStatusMessage());
                    }
                } else {
                    view.showLoginError(view.getContext().getString(R.string.server_error));
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<UserInfo>> call, Throwable t) {
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
