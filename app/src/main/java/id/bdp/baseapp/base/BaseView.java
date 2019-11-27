package id.bdp.baseapp.base;

import id.bdp.baseapp.ContextProvider;

/**
 * Created with love by muhwid on 27/10/18.
 */

public interface BaseView<T extends BasePresenter> extends ContextProvider {
    void showNetworkNotAvailableError();
}
