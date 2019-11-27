package id.bdp.baseapp.data;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import id.bdp.baseapp.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created with love by muhwid on 27/10/18
 **/

public class DataModule {
    public static final String BASE_URL = BuildConfig.BASE_URL;

    public static OkHttpClient getHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }
}

