package com.example.yalantis.y1.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yalantis.y1.R;
import com.example.yalantis.y1.adapter.TabRecyclerAdapter;
import com.example.yalantis.y1.interfaces.IShowedFragment;
import com.example.yalantis.y1.model.TaskBean;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class TabRecyclerFragment extends Fragment implements IShowedFragment, ExploreListView {
    public static TabRecyclerFragment newInstance(int tabNumber) {
        Bundle bundle = new Bundle();
        bundle.putInt(TAB_NUMBER, tabNumber);
        TabRecyclerFragment ticketsFragment = new TabRecyclerFragment();
        ticketsFragment.setArguments(bundle);
        return ticketsFragment;
    }

    private static final String TAB_NUMBER = "tab_number";
    private int mCurrTabNumber;
    private View mView;
    private TabRecyclerAdapter tabRecyclerAdapter;
    private RecyclerView mRvTaskWork;
//    private List<TaskModel> mTaskList;

    private int page = 1;
    private ExplorePresenter explorePresenter;

    private Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tab_recycler, container, false);
        realm = Realm.getDefaultInstance();
        getDataFromBundle();

//        if (mCurrTabNumber == 0) {
//            initViews();
//        fillTaskList();
//            initListeners();
            if (tabRecyclerAdapter == null && mCurrTabNumber == 0) {
//                explorePresenter = new ExplorePresenter(getContext());
//                explorePresenter.attachView(this);
//                page = 1;
//                explorePresenter.loadList(page);
                getTaskList();
            }
//            log(mCurrTabNumber + " - onCreateView___");
//        } else {
//            log(mCurrTabNumber + " - onCreateView");
//        }

//        if (tabRecyclerAdapter == null) {
//            explorePresenter = new ExplorePresenter(getContext());
//            explorePresenter.attachView(this);
//            page = 1;
//            explorePresenter.loadList(page);
//        }

        return mView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        realm.close();
    }

    private void getTaskList() {
        initViews();
        initListeners();

        RealmResults<TaskBean> results/* = realm.where(TaskBean.class).equalTo("state.name", "В роботі").findAll()*/ = null;
//        log(results.size() + " 1 !!!!!!!!!!!!");
//        realm.beginTransaction();
//        log(results.deleteAllFromRealm());
//        realm.commitTransaction();
//        realm = Realm.getDefaultInstance();
        switch (mCurrTabNumber) {
            case 0:
                results = realm.where(TaskBean.class).equalTo("state.name", "В роботі").findAll();
                break;
            case 1:
                results = realm.where(TaskBean.class).equalTo("state.name", "Виконано").findAll();
                break;
            case 2:
                results = realm.where(TaskBean.class).equalTo("state.name", "На модерації").findAll();
                break;
        }

        if (results.size() == 0) {
            explorePresenter = new ExplorePresenter(getContext());
            explorePresenter.attachView(this);
            page = 1;
            explorePresenter.loadList(page, mCurrTabNumber);
        } else {
            tabRecyclerAdapter = new TabRecyclerAdapter(results);
            mRvTaskWork.setAdapter(tabRecyclerAdapter);
        }
    }

    private void getDataFromBundle() {
        mCurrTabNumber = getArguments().getInt(TAB_NUMBER);
    }

    private void initViews() {
        mRvTaskWork = (RecyclerView) mView.findViewById(R.id.rvTaskTabRecycler);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvTaskWork.setLayoutManager(mLayoutManager);
    }

//    private void fillTaskList() {
//        mTaskList = App.contentManager.getTaskList();
//    }

    private LinearLayoutManager mLayoutManager;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    private void initListeners() {
        mRvTaskWork.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {//check for scroll down

                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount) {
//                            loading = false;
//                            page++;
//                            explorePresenter.loadList(page, mCurrTabNumber);
//                            Log.d("mylog", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
                        }
                    }
                }
            }
        });
//        tabRecyclerAdapter = new TabRecyclerAdapter(mTaskList);
//        mRvTaskWork.setAdapter(tabRecyclerAdapter);
    }

    private void log(Object o) {
        Log.d("mylog", o+"");
    }

    @Override
    public void onShowedFragment() {
//        getDataFromBundle();
//        initViews();
//        fillTaskList();
//        initListeners();
        getTaskList();
        log(mCurrTabNumber + " = onShowedFragment");
    }

    @Override
    public void refresh(List<TaskBean> data) {
//        log(data.size() + " - size");
        log("refresh");

        RealmResults<TaskBean> results/* = realm.where(TaskBean.class).equalTo("state.name", "В роботі").findAll()*/ = null;
//        log(results.size() + " 1 !!!!!!!!!!!!");
//        realm.beginTransaction();
//        log(results.deleteAllFromRealm());
//        realm.commitTransaction();
//        realm = Realm.getDefaultInstance();
        switch (mCurrTabNumber) {
            case 0:
                results = realm.where(TaskBean.class).equalTo("state.name", "В роботі").findAll();
                break;
            case 1:
                results = realm.where(TaskBean.class).equalTo("state.name", "Виконано").findAll();
                break;
            case 2:
                results = realm.where(TaskBean.class).equalTo("state.name", "На модерації").findAll();
                break;
        }

//        log(results.size() + " 1 !!!!!!!!!!!!");

//        log(results.size() + " 2 !!!!!!!!!!!!");
//        results = realm.where(TaskBean.class).equalTo("state.id", "1").findAll();
//        log(results.size() + " 3 !!!!!!!!!!!!");
//        mContentsArray.addAll(results);


        tabRecyclerAdapter = new TabRecyclerAdapter(results);
        mRvTaskWork.setAdapter(tabRecyclerAdapter);
    }

    @Override
    public void loadMore(List<TaskBean> data) {
        log("loadMore");
    }

    @Override
    public void showLoading(String msg) {
        log("showLoading");
    }

    @Override
    public void hideLoading() {
        log("hideLoading");
    }

    @Override
    public void showError(String msg, View.OnClickListener onClickListener) {
        log(msg);
        log("showError");
    }

    @Override
    public void showEmpty(String msg, View.OnClickListener onClickListener) {
        log("showEmpty");
    }

    @Override
    public void showEmpty(String msg, View.OnClickListener onClickListener, int imageId) {
        log("showEmpty");
    }

    @Override
    public void showNetError(View.OnClickListener onClickListener) {
        log("showNetError");
    }
}
