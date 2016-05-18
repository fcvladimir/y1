package com.example.yalantis.y1.main;

import com.example.yalantis.y1.events.BusProvider;
import com.example.yalantis.y1.events.TitleTabsLoadDiskEvent;
import com.squareup.otto.Subscribe;

public class MainPresenterImpl implements MainPresenter {

    private MainContract mMainView;
    private MainServiceImpl mMainService;

    public MainPresenterImpl(MainContract mainContract) {
        BusProvider.getInstance().register(this);
        mMainView = mainContract;
        mMainService = new MainServiceImpl();
    }

    @Override
    public void loadSectionsTabs() {
        mMainService.recoverTitleTabs();
    }

    @Subscribe
    public void onLoadTitleTabsDiskSuccess(TitleTabsLoadDiskEvent titleTabsLoadDiskEvent) {
        mMainView.initPagerAdapter(titleTabsLoadDiskEvent.getListTitleTabs());
    }

    @Override
    public void onDestroy() {
        BusProvider.getInstance().unregister(this);
        mMainService.onDestroy();
        mMainService = null;
    }
}
