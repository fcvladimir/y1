package com.example.yalantis.y1.ui.fragment;

public interface TabRecyclerContract extends MvpView {

    void initPresenter();

    void refresh();

    void loadMore(boolean isLoadMore);

}