package id.lima.baseapp.util;

import android.app.Activity;

public class TransitionUtil {

    public static TransitionUtil instance;

    public static TransitionUtil getInstance() {
        if (instance == null)
            instance = new TransitionUtil();
        return instance;
    }

    public void animFade(Activity ctx) {
        ctx.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void animRight(Activity ctx) {
        ctx.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
