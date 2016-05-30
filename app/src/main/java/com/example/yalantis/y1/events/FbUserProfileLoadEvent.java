package com.example.yalantis.y1.events;

import com.example.yalantis.y1.model.ProfileModel;

public class FbUserProfileLoadEvent {
    private ProfileModel mListTaskBean;

    public FbUserProfileLoadEvent(ProfileModel listTaskBean) {
        this.mListTaskBean = listTaskBean;
    }

    public ProfileModel getTaskBeanList() {
        return mListTaskBean;
    }

}
