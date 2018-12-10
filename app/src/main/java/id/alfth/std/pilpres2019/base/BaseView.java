package id.alfth.std.pilpres2019.base;

import id.alfth.std.pilpres2019.ContextProvider;

/**
 * Created with love by muhwid on 27/10/18.
 */

public interface BaseView<T extends BasePresenter> extends ContextProvider {
    void showNetworkNotAvailableError();
}
