package com.example.yalantis.y1.fragment;

public interface ExploreListView extends MvpView {

    void initPresenter();

    void refresh();

    void loadMore();

}