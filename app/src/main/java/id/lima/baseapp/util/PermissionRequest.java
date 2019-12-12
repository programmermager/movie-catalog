package id.lima.baseapp.util;

import android.Manifest;
import android.app.Activity;
import androidx.core.app.ActivityCompat;

/**
 * Created by muhwid on 20/02/18.
 */

public class PermissionRequest {
    public static void requestLocation(Activity context, int reqCode) {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, reqCode);
    }

    public static void requestWriteReadExtStorage(Activity context, int reqCode) {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, reqCode);
    }

    public static void requestCallPermission(Activity context, int reqCode) {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CALL_PHONE}, reqCode);
    }

    public static void requestCameraPermission(Activity context, int reqCode) {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA}, reqCode);
    }

    public static void requestReadPhonePermission(Activity context, int reqCode) {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_PHONE_STATE}, reqCode);
    }
}
