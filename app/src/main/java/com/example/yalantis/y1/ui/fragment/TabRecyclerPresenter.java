package com.example.yalantis.y1.ui.fragment;

public interface TabRecyclerPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
