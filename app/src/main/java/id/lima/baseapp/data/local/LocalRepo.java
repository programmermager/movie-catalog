package id.lima.baseapp.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;

import id.lima.baseapp.data.model.UserInfo;

/**
 * Created by muhwid on 27/10/18.
 */

public class LocalRepo {

    private static final String USER_INFO = "_user_info";
    private final SharedPreferences sharedPreferences;
    private final Gson gson;
    private UserInfo cachedUserInfo;
    private static LocalRepo localRepo;

    public static LocalRepo getInstance(Context context) {
        if (localRepo == null) {
            localRepo = new LocalRepo(context, new Gson());
        }
        return localRepo;
    }

    private LocalRepo(Context context, Gson gson) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.gson = gson;
    }

    public void setUserInfo(@NonNull UserInfo userInfo) {
        sharedPreferences.edit().putString(USER_INFO, gson.toJson(userInfo)).apply();
        cachedUserInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        if (cachedUserInfo == null) {
            String userInfoS = sharedPreferences.getString(USER_INFO, null);
            if (!TextUtils.isEmpty(userInfoS)) {
                cachedUserInfo = gson.fromJson(userInfoS, UserInfo.class);
            }
        }
        return cachedUserInfo;
    }

    public void logout() {
        this.cachedUserInfo = null;
        sharedPreferences.edit().clear().apply();
    }
}
