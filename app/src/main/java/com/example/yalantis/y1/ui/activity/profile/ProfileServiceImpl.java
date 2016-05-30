package com.example.yalantis.y1.ui.activity.profile;

public class ProfileServiceImpl implements ProfileService {

    private ProfileRepositoryDiskImpl mProfileRepositoryDisk;

    public ProfileServiceImpl() {
        mProfileRepositoryDisk = new ProfileRepositoryDiskImpl();
    }

    @Override
    public void getFbUserProfile() {
        mProfileRepositoryDisk.getFbUserProfile();
    }

    @Override
    public void onDestroy() {
        mProfileRepositoryDisk.onDestroy();
        mProfileRepositoryDisk = null;
    }
}
