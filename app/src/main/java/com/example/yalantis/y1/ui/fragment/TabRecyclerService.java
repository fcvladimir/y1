package com.example.yalantis.y1.ui.fragment;

import com.example.yalantis.y1.model.task.TaskModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface TabRecyclerService {
    String STATE  = "state";
    String AMOUNT = "amount";
    String OFFSET = "offset";

    @GET("/rest/v1/tickets")
    Observable<List<TaskModel>> getTaskList(@Query(STATE) String state,
                                            @Query(AMOUNT) int limit,
                                            @Query(OFFSET) int offset);
}
