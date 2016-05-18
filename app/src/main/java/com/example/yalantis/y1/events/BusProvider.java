package com.example.yalantis.y1.events;

import android.content.Context;

import com.example.yalantis.y1.App;
import com.squareup.otto.Bus;

public class BusProvider {
    private static Bus mBus;

    private static Bus getBus(Context context) {
        if (mBus == null) {
            synchronized (BusProvider.class) {
                if (mBus == null) {
                    mBus = new Bus();
                }
            }
        }
        return mBus;
    }

    public static Bus getInstance() {
        return getBus(App.getInstance());
    }

    public static void shutdown() {
        mBus = null;
    }

}
