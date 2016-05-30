package com.example.yalantis.y1.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yalantis.y1.App;
import com.example.yalantis.y1.R;
import com.example.yalantis.y1.ui.adapter.TabRecyclerAdapter;
import com.example.yalantis.y1.interfaces.IShowedFragment;
import com.example.yalantis.y1.model.task.TaskModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class TabRecyclerFragment extends Fragment implements IShowedFragment, TabRecyclerContract {
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
    private TabRecyclerAdapter mTabRecyclerAdapter;
    @BindView(R.id.rvTaskTabRecycler)
    RecyclerView mRvTaskWork;
    @BindView(R.id.srlTaskRefresh)
    SwipeRefreshLayout mSrlTaskRefresh;

    private LinearLayoutManager mLayoutManager;
    private boolean mIsLoading = true;
    private int mPastVisiblesItems, mVisibleItemCount, mTotalItemCount;

    private int mPage;
    private TabRecyclerPresenterImpl mTabRecyclerPresenterImpl;

    private RealmResults<TaskModel> mResultsToShow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tab_recycler, container, false);
        getDataFromBundle();

        if (mCurrTabNumber == 0) { // need to show data only in visible tab
            initViews();
            initPresenter();
            initListeners();
            getTaskList();
        }

        return mView;
    }

    private void getTaskList() {
        getTaskListByStateName();
        if (mResultsToShow.size() == 0) {
            mPage = 1;
            mTabRecyclerPresenterImpl.loadList(mPage, mCurrTabNumber);
        } else {
            mTabRecyclerAdapter = new TabRecyclerAdapter(mResultsToShow);
            mRvTaskWork.setAdapter(mTabRecyclerAdapter);
        }
    }

    private void getDataFromBundle() {
        mCurrTabNumber = getArguments().getInt(TAB_NUMBER);
    }

    private void initViews() {
        ButterKnife.bind(this, mView);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvTaskWork.setLayoutManager(mLayoutManager);
    }

    private void initListeners() {
        mSrlTaskRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mIsLoading = true;
                App.contentManager.deleteTasksFromDb(mResultsToShow);
                mPage = 1;
                mTabRecyclerPresenterImpl.loadList(mPage, mCurrTabNumber);
            }
        });
        mRvTaskWork.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {//check for scroll down
                    mVisibleItemCount = mLayoutManager.getChildCount();
                    mTotalItemCount = mLayoutManager.getItemCount();
                    mPastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                    if (mIsLoading) {
                        if ((mVisibleItemCount + mPastVisiblesItems) >= mTotalItemCount) {
                            mIsLoading = false;
                            mPage++;
                            mTabRecyclerPresenterImpl.loadList(mPage, mCurrTabNumber);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onShowedFragment() { // need to show data only in visible tab
        initViews();
        initPresenter();
        initListeners();
        getTaskList();
    }

    @Override
    public void initPresenter() {
        mTabRecyclerPresenterImpl = new TabRecyclerPresenterImpl(getContext());
        mTabRecyclerPresenterImpl.attachView(this);
    }

    @Override
    public void refresh() {
        mSrlTaskRefresh.setRefreshing(false);
        getTaskListByStateName();
        mTabRecyclerAdapter = new TabRecyclerAdapter(mResultsToShow);
        mRvTaskWork.setAdapter(mTabRecyclerAdapter);
    }

    @Override
    public void loadMore(boolean isLoadMore) {
        mIsLoading = isLoadMore;
        getTaskListByStateName();
        mTabRecyclerAdapter.notifyDataSetChanged();
    }

    private void getTaskListByStateName() {
        mResultsToShow = App.contentManager.getTasksFromDb(mCurrTabNumber);
    }

    @Override
    public void showError(String msg) {
        mIsLoading = true;
        mSrlTaskRefresh.setRefreshing(false);
    }

}
