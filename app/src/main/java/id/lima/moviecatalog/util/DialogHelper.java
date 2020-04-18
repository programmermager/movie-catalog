package id.lima.moviecatalog.util;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import android.view.View;
import android.view.Window;

import id.lima.moviecatalog.R;
import id.lima.moviecatalog.interfaces.DialogClickListener;

/**
 * Created with love by muhwid on 04/02/19.
 */

public class DialogHelper {
    private static DialogHelper instance;

    public static DialogHelper getInstance() {
        if (instance == null) {
            instance = new DialogHelper();
        }
        return instance;
    }

    public AppCompatDialog initCustomDialog(Context context, View dialogView) {
        AppCompatDialog dialog = new AppCompatDialog(context, R.style.CustomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(dialogView);
        dialog.setCanceledOnTouchOutside(true);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        return dialog;
    }

    public void showAlertDialog(Context context, String title, String content, String yes, String no, final DialogClickListener listener) {
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(context);

        if (title != null)
            alertDialog2.setTitle(title);

        alertDialog2.setMessage(content);

        alertDialog2.setIcon(R.drawable.ic_launcher_background);

        if (yes != null) {
            alertDialog2.setPositiveButton(yes,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            listener.onClickYes();
                            dialog.cancel();
                        }
                    });
        }

        if (no != null) {
            alertDialog2.setNegativeButton(no,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            listener.onClickNo();
                            dialog.cancel();
                        }
                    });
        }

        alertDialog2.show();
    }
}

