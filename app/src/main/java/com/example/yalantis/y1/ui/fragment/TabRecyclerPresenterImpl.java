package com.example.yalantis.y1.ui.fragment;

import android.content.Context;

import com.example.yalantis.y1.App;
import com.example.yalantis.y1.model.task.TaskModel;
import com.example.yalantis.y1.retrofit.ApiUtils;
import com.example.yalantis.y1.retrofit.RetrofitService;

import java.util.List;

import io.realm.Realm;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class TabRecyclerPresenterImpl extends BasePresenter<TabRecyclerContract> {

    public static final int LIMIT = 10;

    private TabRecyclerService mTabRecyclerService;
    private Subscription mSubscription;

    public TabRecyclerPresenterImpl(Context context) {
        mTabRecyclerService = RetrofitService.createApi(context, TabRecyclerService.class);
    }

    @Override
    public void attachView(TabRecyclerContract tabRecyclerContract) {
        super.attachView(tabRecyclerContract);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadList(final int page, int taskType) {
        String state = "";
        switch (taskType) {
            case 0:
                state = ApiUtils.TASK_STATE_WORK;
                break;
            case 1:
                state = ApiUtils.TASK_STATE_DONE;
                break;
            case 2:
                state = ApiUtils.TASK_STATE_PENDING;
                break;
        }

        checkViewAttached();

        int offset = (page - 1) * LIMIT;
        mSubscription = mTabRecyclerService.getTaskList(state, LIMIT, offset)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Func1<Throwable, List<TaskModel>>() {
                    @Override
                    public List<TaskModel> call(Throwable throwable) {
                        // in case of error we return what we expect
                        return null;
                    }
                })
                .doOnNext(new Action1<List<TaskModel>>() {
                    @Override
                    public void call(List<TaskModel> taskBeen) {
                        // caching data
                        Realm realm = Realm.getDefaultInstance();
                        App.contentManager.saveTasksToDb(realm, taskBeen);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<TaskModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<TaskModel> taskBeen) {
                        if (page == 1) {
                            getMvpView().refresh();
                        } else {
                            if (taskBeen.size() < LIMIT) {
                                getMvpView().loadMore(false);
                            } else {
                                getMvpView().loadMore(true);
                            }
                        }
                    }
                });
    }

}