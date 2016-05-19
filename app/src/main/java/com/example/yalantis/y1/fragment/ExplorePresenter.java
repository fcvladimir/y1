package com.example.yalantis.y1.fragment;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.yalantis.y1.model.TaskBean;

import java.util.List;

import io.realm.Realm;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ExplorePresenter extends BasePresenter<ExploreListView> {

    private static final String TASK_TYPE_WORK    = "0,9,5,7,8";
    private static final String TASK_TYPE_DONE    = "10,6";
    private static final String TASK_TYPE_PENDING = "1,3,4";

    public static final int LIMIT = 10;

    private ExploreListService exploreListService;
    private Subscription mSubscription;

    public ExplorePresenter(Context context) {
        exploreListService = RetrofitUtils.createApi(context, ExploreListService.class);
    }

    @Override
    public void attachView(ExploreListView mvpView) {
        super.attachView(mvpView);
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
                state = TASK_TYPE_WORK;
                break;
            case 1:
                state = TASK_TYPE_DONE;
                break;
            case 2:
                state = TASK_TYPE_PENDING;
                break;
        }

        checkViewAttached();

        int offset = (page - 1) * LIMIT;

        mSubscription = exploreListService.getTaskList(state, LIMIT, offset)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Func1<Throwable, List<TaskBean>>() {
                    @Override
                    public List<TaskBean> call(Throwable throwable) {
                        Log.d("mylog", "on ERROR return");
                        return null;
                    }
                })
                .doOnNext(new Action1<List<TaskBean>>() {
                    @Override
                    public void call(List<TaskBean> taskBeen) {
                        Log.d("mylog", "cache ????");
                    }
                }) // кешируем
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<TaskBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showError(e.getMessage(), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                    }

                    @Override
                    public void onNext(List<TaskBean> articleListBeanList) {

                        Log.d("mylog", "onNext: 1");
                        Realm realm = Realm.getDefaultInstance();
                        Log.d("mylog", "onNext: 2");
                        realm.beginTransaction();
                        Log.d("mylog", "onNext: 3");
                        realm.copyToRealmOrUpdate(articleListBeanList);
                        Log.d("mylog", "onNext: 4");
                        realm.commitTransaction();
                        Log.d("mylog", "onNext: 5");

//                        RealmResults<TaskBean> allNewPosts = articleListBeanList.get(8);
//
////                        Realm realm = Realm.getInstance(mContext);
//                        realm.beginTransaction();
//                        TaskBean p = realm.createObject(TaskBean.class);
//                        p.setId(id);
//                        p.setName(name);
//                        p.setToken(token);
//                        p.setPhoto(photo);
//                        p.setEmail(email);
//                        realm.commitTransaction();



//                        final Realm realm1 = Realm.getInstance(application);
//                        RealmResults<TaskBean> allNewPosts = realm1.where(TaskBean.class).findAll();
                        if (page == 1) {
                            getMvpView().refresh(articleListBeanList);
                        } else {
                            getMvpView().loadMore(articleListBeanList);
                        }

//                        callback.onListDataUpdated(TaskBean.from(allNewPosts));
//                        realm1.close();
                    }
                });

    }

//    private void saveDataToDBorSP(ArticleListEntity articleListEntity) {
//
//    }


}