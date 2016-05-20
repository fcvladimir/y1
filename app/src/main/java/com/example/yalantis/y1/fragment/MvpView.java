package com.example.yalantis.y1.fragment;

import android.view.View;

public interface MvpView {

    void showLoading(String msg);

    void hideLoading();

    void showError(String msg, View.OnClickListener onClickListener);

    void showEmpty(String msg, View.OnClickListener onClickListener);

    void showEmpty(String msg, View.OnClickListener onClickListener, int imageId);

    void showNetError(View.OnClickListener onClickListener);

}
