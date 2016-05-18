package com.example.yalantis.y1.fragment;

import com.example.yalantis.y1.model.TaskBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ExploreListService {
    String AMOUNT = "amount";
    String OFFSET = "offset";

    @GET("/rest/v1/tickets")
    Observable<List<TaskBean>> getTaskList(@Query("state") String state,
                                           @Query(AMOUNT) int limit,
                                           @Query(OFFSET) int offset);
}
