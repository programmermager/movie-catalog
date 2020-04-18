package id.lima.moviecatalog.base;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import id.lima.moviecatalog.R;
import id.lima.moviecatalog.util.view.SlideShowDialogFragment;

/**
 * Created with love by muhwid on 27/10/18
 **/
public class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    protected void setupToolbar(int toolbarId, String text, int icBack) {
        toolbar = findViewById(toolbarId);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(icBack);

        TextView tvTitle = toolbar.findViewById(R.id.title);
        tvTitle.setText(text);

        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
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
