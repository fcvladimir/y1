package com.example.yalantis.y1.ui.activity.main;

public class MainServiceImpl implements MainService {

    private MainRepositoryDiskImpl mMainRepositoryDisk;

    public MainServiceImpl() {
        mMainRepositoryDisk = new MainRepositoryDiskImpl();
    }

    @Override
    public void recoverTitleTabs() {
        mMainRepositoryDisk.recoverTitleTabs();
    }

    @Override
    public void onDestroy() {
        mMainRepositoryDisk.onDestroy();
        mMainRepositoryDisk = null;
    }
}
