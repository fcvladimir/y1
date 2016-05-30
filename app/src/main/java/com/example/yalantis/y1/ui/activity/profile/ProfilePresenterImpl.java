package com.example.yalantis.y1.ui.activity.profile;

import com.example.yalantis.y1.events.BusProvider;
import com.example.yalantis.y1.events.FbUserProfileLoadEvent;
import com.squareup.otto.Subscribe;

public class ProfilePresenterImpl implements ProfilePresenter {

    private ProfileContract mProfileContract;
    private ProfileServiceImpl mProfileService;

    public ProfilePresenterImpl(ProfileContract mainContract) {
        BusProvider.getInstance().register(this);
        mProfileContract = mainContract;
        mProfileService = new ProfileServiceImpl();
    }

    @Override
    public void loadFbUserProfile() {
        mProfileService.getFbUserProfile();
    }

    @Subscribe
    public void onLoadFbUserProfile(FbUserProfileLoadEvent fbUserProfileLoadEvent) {
        mProfileContract.fillUserData(fbUserProfileLoadEvent.getTaskBeanList());
    }

    @Override
    public void onDestroy() {
        BusProvider.getInstance().unregister(this);
        mProfileService.onDestroy();
        mProfileService = null;
    }
}
