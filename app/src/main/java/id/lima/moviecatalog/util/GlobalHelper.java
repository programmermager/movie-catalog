package id.lima.moviecatalog.util;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import id.lima.moviecatalog.R;
import retrofit2.Call;

/**
 * Created by muhwid on 08/03/18.
 */

public class GlobalHelper {

    public static GlobalHelper instance;

    private static String TAG = GlobalHelper.class.getSimpleName();
    private static Dialog loading;

    public static GlobalHelper getInstance() {
        if (instance == null)
            instance = new GlobalHelper();

        return instance;
    }

    public String formatingNumber(int number) {
        Locale locale = Locale.GERMAN;
        return NumberFormat.getNumberInstance(locale).format(number);
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && (networkInfo.isConnected());
    }

    public void cancel(Call... calls) {
        if (calls != null) {
            for (Call call : calls) {
                if (call != null)
                    call.cancel();
            }
        }
    }

    public void loadLoading(Context context) {
        if (loading == null) {
            loading = new Dialog(context);
            loading.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            loading.setCancelable(false);
            loading.setCanceledOnTouchOutside(false);
            loading.requestWindowFeature(Window.FEATURE_NO_TITLE);
            loading.setContentView(R.layout.loading);
            loading.show();
        }
    }

    public void dissmisLoading() {
        if (loading != null) {
            if (loading.isShowing()) {
                loading.dismiss();
                loading = null;
            }
        }
    }

    public void makeToast(String id, Context context) {
        Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
    }


    public boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }

    public void animFadeIn(View view) {
        view.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeIn)
                .duration(700)
                .playOn(view);
    }

    public void animFadeOut(View view) {
        YoYo.with(Techniques.FadeOut)
                .duration(700)
                .playOn(view);

        view.setVisibility(View.GONE);
    }

    public void animTada(View view) {
        YoYo.with(Techniques.Tada)
                .duration(700)
                .playOn(view);
    }

    public static Intent launchDirection(String latitude, String longitude) {
        String strUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude;
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        return intent;
    }

    public void launchUrl(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(Intent.createChooser(intent, "Browse with"));
    }
}
