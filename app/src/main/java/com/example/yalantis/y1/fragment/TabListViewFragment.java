package com.example.yalantis.y1.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.yalantis.y1.App;
import com.example.yalantis.y1.R;
import com.example.yalantis.y1.adapter.TabListViewAdapter;
import com.example.yalantis.y1.model.TaskModel;

import java.util.List;

public class TabListViewFragment extends Fragment {
    public static TabListViewFragment newInstance() {
        return new TabListViewFragment();
    }

    private ListView mLvTaskPending;
    private List<TaskModel> mTaskList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_tab_listview, container, false);

        initViews(fragmentView);
        //[Comment] Wrong formatting
        fillTaskList();

        initListeners();

        return fragmentView;
    }

    private void initViews(View v) {
        mLvTaskPending = (ListView) v.findViewById(R.id.lvTaskTabListView);
    }

    private void fillTaskList() {
        mTaskList = App.contentManager.getTaskList();
    }

    private void initListeners() {
        TabListViewAdapter taskStatusRecyclerAdapter = new TabListViewAdapter(getContext(), mTaskList);
        mLvTaskPending.setAdapter(taskStatusRecyclerAdapter);
    }

}
