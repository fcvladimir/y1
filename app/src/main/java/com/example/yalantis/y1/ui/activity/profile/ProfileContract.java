package com.example.yalantis.y1.ui.activity.profile;

import com.example.yalantis.y1.model.ProfileModel;

public interface ProfileContract {

    void initViews();

    void initPresenter();

    void loadFbUserProfile();

    void fillUserData(ProfileModel profileModel);

}
