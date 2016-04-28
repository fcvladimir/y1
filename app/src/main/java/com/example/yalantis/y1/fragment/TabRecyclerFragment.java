package com.example.yalantis.y1.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yalantis.y1.App;
import com.example.yalantis.y1.R;
import com.example.yalantis.y1.adapter.TabRecyclerAdapter;
import com.example.yalantis.y1.model.TaskModel;

import java.util.List;

public class TabRecyclerFragment extends Fragment {
    public static TabRecyclerFragment newInstance() {
        return new TabRecyclerFragment();
    }

    private View mView;
    private RecyclerView mRvTaskWork;
    private List<TaskModel> mTaskList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tab_recycler, container, false);

        initViews();
        fillTaskList();
        initListeners();

        return mView;
    }

    private void initViews() {
        mRvTaskWork = (RecyclerView) mView.findViewById(R.id.rvTaskTabRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvTaskWork.setLayoutManager(linearLayoutManager);
    }

    private void fillTaskList() {
        mTaskList = App.contentManager.getTaskList();
    }

    private void initListeners() {
        TabRecyclerAdapter tabRecyclerAdapter = new TabRecyclerAdapter(mTaskList);
        mRvTaskWork.setAdapter(tabRecyclerAdapter);
    }

}
