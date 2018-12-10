package id.alfth.std.pilpres2019.util.view;

import android.app.AlertDialog;
import android.content.Context;

import id.alfth.std.pilpres2019.R;


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
