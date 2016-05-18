package com.example.yalantis.y1.main;

import java.util.List;

public interface MainContract {

    void initViews();

    void initPresenter();

    void loadSectionsTabs();

    void initPagerAdapter(List<String> listTitleTabs);

    void initListeners();
}
