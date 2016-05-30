package com.example.yalantis.y1.ui.activity.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yalantis.y1.R;
import com.example.yalantis.y1.model.ProfileModel;
import com.example.yalantis.y1.util.ImageUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements ProfileContract {

    @BindView(R.id.ivProfilePhoto)
    ImageView mIvProfilePhoto;
    @BindView(R.id.tvProfileName)
    TextView mTvProfileName;

    private ProfilePresenterImpl mProfilePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();
        initPresenter();
        loadFbUserProfile();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProfilePresenter.onDestroy();
    }

    @Override
    public void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    public void initPresenter() {
        mProfilePresenter = new ProfilePresenterImpl(this);
    }

    @Override
    public void loadFbUserProfile() {
        mProfilePresenter.loadFbUserProfile();
    }

    @Override
    public void fillUserData(ProfileModel profileModel) {
        if (profileModel.getPicture() != null) {
            ImageLoader.getInstance().displayImage(profileModel.getPicture(), mIvProfilePhoto, ImageUtils.UIL_USER_PHOTO_DISPLAY_OPTIONS);
        }
        mTvProfileName.setText(profileModel.getName());
    }

}
