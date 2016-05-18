package com.example.yalantis.y1.main;

import com.example.yalantis.y1.events.TitleTabsLoadDiskEvent;
import com.squareup.otto.Subscribe;

public interface MainPresenter {

    void loadSectionsTabs();

    @Subscribe
    void onLoadTitleTabsDiskSuccess(TitleTabsLoadDiskEvent titleTabsLoadDiskEvent);

    void onDestroy();
}
