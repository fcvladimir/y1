package com.example.yalantis.y1.fragment;

import android.content.Context;
import android.view.View;

import com.example.yalantis.y1.model.TaskBean;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by fangxiao on 16/1/28.
 */
public class ExplorePresenter extends BasePresenter<ExploreListView> {

    private static final String tag = "0,9,5,7,8";

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

    public void loadList(final int page) {
        checkViewAttached();

        int offset = (page - 1) * LIMIT;

        mSubscription = exploreListService.getTaskList(tag, LIMIT, offset)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map(new Func1<List<TaskBean>, List<TaskBean>>() {
                    @Override
                    public List<TaskBean> call(List<TaskBean> articleListBeanList) {
//                        for (TaskBean articleListBean : articleListBeanList) {
//                            articleListBean.setSummary(BaseUtil.delHTMLTag(articleListBean.getSummary()));
//                            articleListBean.setContent(BaseUtil.delHTMLTag(articleListBean.getContent()));
//                        }
                        return articleListBeanList;
                    }
                })
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

                        if (page == 1) {
                            getMvpView().refresh(articleListBeanList);
                        } else {
                            getMvpView().loadMore(articleListBeanList);
                        }
                    }
                });

    }

//    private void saveDataToDBorSP(ArticleListEntity articleListEntity) {
//
//    }


}