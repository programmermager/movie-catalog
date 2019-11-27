package id.bdp.baseapp.util.view;

import android.app.AlertDialog;
import android.content.Context;

import id.bdp.baseapp.R;


/**
 * Created by muhwid on 5/27/2017.
 */

public class LoadingWindow extends AlertDialog
{

    public LoadingWindow(Context context) {
        super(context);
    }

    @Override
    public void show() {
        super.show();
        setContentView(R.layout.loading);

    }

}
