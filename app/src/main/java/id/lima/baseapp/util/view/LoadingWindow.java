package id.lima.baseapp.util.view;

import android.app.AlertDialog;
import android.content.Context;

import id.lima.baseapp.R;


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
