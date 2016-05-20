package com.example.yalantis.y1.fragment;

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
