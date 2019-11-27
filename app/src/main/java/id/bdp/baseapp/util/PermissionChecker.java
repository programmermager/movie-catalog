package id.bdp.baseapp.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;

/**
 * Created by muhwid on 1/24/18.
 */

public class PermissionChecker {
    public static boolean checkLocation(Context context) {
        String permission = Manifest.permission.ACCESS_FINE_LOCATION;
        int res = context.checkCallingOrSelfPermission(permission);

        String permission2 = Manifest.permission.ACCESS_COARSE_LOCATION;
        int res2 = context.checkCallingOrSelfPermission(permission2);

        return (res == PackageManager.PERMISSION_GRANTED && res2 == PackageManager.PERMISSION_GRANTED);
    }

    public static boolean isGPSActive(Context context) {
        final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static boolean isReadPhoneStateActive(Context context) {
        String permission = Manifest.permission.READ_PHONE_STATE;
        int res = context.checkCallingOrSelfPermission(permission);

        return (res == PackageManager.PERMISSION_GRANTED);
    }

    public static boolean checkWriteReadExternalStorage(Context context) {
        String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int res = context.checkCallingOrSelfPermission(permission);

        String permission2 = Manifest.permission.READ_EXTERNAL_STORAGE;
        int res2 = context.checkCallingOrSelfPermission(permission2);

        return (res == PackageManager.PERMISSION_GRANTED && res2 == PackageManager.PERMISSION_GRANTED);
    }

    public static boolean checkCamera(Context context) {
        String permission = Manifest.permission.CAMERA;
        int res = context.checkCallingOrSelfPermission(permission);

        return (res == PackageManager.PERMISSION_GRANTED);
    }

    public static boolean checkCallPermission(Context context) {
        String permission = Manifest.permission.CALL_PHONE;
        int res = context.checkCallingOrSelfPermission(permission);

        return (res == PackageManager.PERMISSION_GRANTED);
    }
}
