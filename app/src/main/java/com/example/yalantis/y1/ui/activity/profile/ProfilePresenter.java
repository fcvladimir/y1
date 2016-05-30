package com.example.yalantis.y1.ui.activity.profile;

import com.example.yalantis.y1.events.FbUserProfileLoadEvent;
import com.squareup.otto.Subscribe;

public interface ProfilePresenter {

    void loadFbUserProfile();

    @Subscribe
    void onLoadFbUserProfile(FbUserProfileLoadEvent fbUserProfileLoadEvent);

    void onDestroy();
}
