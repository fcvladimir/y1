package com.example.yalantis.y1.ui.activity.main;

import android.content.res.Resources;
import android.util.Log;

import com.example.yalantis.y1.App;
import com.example.yalantis.y1.R;
import com.example.yalantis.y1.events.BusProvider;
import com.example.yalantis.y1.events.TitleTabsLoadDiskEvent;

import java.util.Arrays;
import java.util.List;

public class MainRepositoryDiskImpl implements MainRepository {
    private static final String TAG = MainRepositoryDiskImpl.class.getSimpleName();

    public MainRepositoryDiskImpl() {
        BusProvider.getInstance().register(this);
    }

    @Override
    public void recoverTitleTabs() {
        try {
            List<String> listTitleTabs = Arrays.asList(App.getInstance().getResources().getStringArray(R.array.main_tabs_title));

            BusProvider.getInstance().post(new TitleTabsLoadDiskEvent(listTitleTabs));
        } catch (Resources.NotFoundException notFoundExcepetion) {
            Log.e(TAG, "Error Getting The Array", notFoundExcepetion);
        }
    }

    @Override
    public void onDestroy() {
        BusProvider.getInstance().unregister(this);
    }
}
