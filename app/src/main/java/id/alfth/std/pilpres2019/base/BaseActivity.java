package id.alfth.std.pilpres2019.base;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import id.alfth.std.pilpres2019.util.view.SlideShowDialogFragment;

/**
 * Created with love by muhwid on 27/10/18
 **/
public class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    protected void setupToolbar(int toolbarId,int bgColor, boolean allowBack, @DrawableRes int image) {
        toolbar = findViewById(toolbarId);
        toolbar.setBackgroundColor(getResources().getColor(bgColor));
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");
    }

    protected void openImageFull(String uri) {
        ArrayList<String> imagesGallery = new ArrayList<>();
        imagesGallery.add(uri);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("images", imagesGallery);
        bundle.putInt("position", 0);
        bundle.putInt("totalImages", 1);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        SlideShowDialogFragment newFragment = SlideShowDialogFragment.newInstance();
        newFragment.setArguments(bundle);
        newFragment.show(ft, "slideshow");
    }

}
