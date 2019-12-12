package id.lima.baseapp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import id.lima.baseapp.util.view.LoadingWindow;
import retrofit2.Call;

/**
 * Created by muhwid on 08/03/18.
 */

public class GlobalHelper {

    public static GlobalHelper instance;

    private static String TAG = GlobalHelper.class.getSimpleName();
    private static LoadingWindow loading;

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
            loading = new LoadingWindow(context);
            loading.setCancelable(false);
            loading.setCanceledOnTouchOutside(false);
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

    public String getValue(EditText id) {
        return id.getText().toString().trim();
    }

    public Boolean checkIsEmpty(EditText viewId) {
        if (TextUtils.isEmpty(viewId.getText().toString().trim())) {
            return true;
        }
        return false;
    }

    public long convertDateEventToTimestamp(String tgl) {
        if (tgl != null && !tgl.equals("")) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = format.parse(tgl);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long timestamp = date.getTime();
            return timestamp;
        } else {
            return -1;
        }
    }

    public String encodeFileToBase64Binary(String filePath) {
        String base64Image = "";
        File imgFile = new File(filePath);
        if (imgFile.exists() && imgFile.length() > 0) {
            Bitmap bm = BitmapFactory.decodeFile(filePath);
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, bOut);
            base64Image = Base64.encodeToString(bOut.toByteArray(), Base64.DEFAULT);
        }

        return base64Image;
    }

    public String encodeFileToBase64BinaryNonImage(String fileName)
            throws IOException {

        File file = new File(fileName);
        byte[] bytes = loadFile(file);
        String encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);
        return "dataTransportModel:application/pdf;base64," + encodedString;
    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
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
}
