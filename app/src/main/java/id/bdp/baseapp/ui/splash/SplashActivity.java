package id.bdp.baseapp.ui.splash;

import android.os.Bundle;

import id.bdp.baseapp.R;
import id.bdp.baseapp.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

//    private void showDialogGeneralInfo(String type) {
//        View dialogView = getLayoutInflater().inflate(R.layout.dialog_info_general, null);
//        dialogInfoGeneralFacility = DialogHelper.getInstance().initCustomDialog(this, dialogView);
//
//        final TextView tvTitle = dialogInfoGeneralFacility.findViewById(R.id.tv_title);
//        final TextView tvContent = dialogInfoGeneralFacility.findViewById(R.id.tv_content);
//        MaterialRippleLayout mrClose = dialogInfoGeneralFacility.findViewById(R.id.mr_close);
//
//        tvTitle.setText((type.contains("general")) ? getString(R.string.title_info_general_facilities) : (type.contains("room")) ? getString(R.string.title_info_room_facilities) : getString(R.string.title_info_add_cost));
//        tvContent.setText((type.contains("general")) ? getString(R.string.detail_content_info_general_facilities) : (type.contains("room")) ? getString(R.string.detail_content_info_room_facilities) : getString(R.string.detail_content_info_add_cost));
//
//        mrClose.setOnClickListener(v -> dialogInfoGeneralFacility.dismiss());
//
//        dialogInfoGeneralFacility.show();
//    }
}
