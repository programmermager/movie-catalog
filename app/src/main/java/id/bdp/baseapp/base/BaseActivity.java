package id.bdp.baseapp.base;

import android.os.Bundle;
import androidx.annotation.DrawableRes;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import id.bdp.baseapp.util.view.SlideShowDialogFragment;

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
