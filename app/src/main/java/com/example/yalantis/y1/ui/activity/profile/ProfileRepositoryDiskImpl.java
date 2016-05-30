package com.example.yalantis.y1.ui.activity.profile;

import android.util.Log;

import com.example.yalantis.y1.App;
import com.example.yalantis.y1.events.BusProvider;
import com.example.yalantis.y1.events.FbUserProfileLoadEvent;
import com.example.yalantis.y1.model.ProfileModel;

public class ProfileRepositoryDiskImpl implements ProfileRepository {
    private static final String TAG = ProfileRepositoryDiskImpl.class.getSimpleName();

    public ProfileRepositoryDiskImpl() {
        BusProvider.getInstance().register(this);
    }

    @Override
    public void getFbUserProfile() {
        try {
            ProfileModel profileModel = App.contentManager.getUserProfile();
            BusProvider.getInstance().post(new FbUserProfileLoadEvent(profileModel));
        } catch (Exception exception) {
            Log.e(TAG, "Error Getting User Profile", exception);
        }
    }

    @Override
    public void onDestroy() {
        BusProvider.getInstance().unregister(this);
    }
}
