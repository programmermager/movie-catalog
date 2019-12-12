package id.lima.baseapp.base;

import id.lima.baseapp.ContextProvider;

/**
 * Created with love by muhwid on 27/10/18.
 */

public interface BaseView<T extends BasePresenter> extends ContextProvider {
    void showNetworkNotAvailableError();
}
