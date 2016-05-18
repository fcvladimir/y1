package com.example.yalantis.y1.fragment;

import com.example.yalantis.y1.model.TaskBean;

import java.util.List;

public interface ExploreListView extends MvpView {

    void refresh(List<TaskBean> data);

    void loadMore(List<TaskBean> data);

}