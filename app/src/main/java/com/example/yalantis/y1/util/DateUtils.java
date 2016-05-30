package com.example.yalantis.y1.util;

import android.content.Context;
import android.text.format.DateFormat;

import com.example.yalantis.y1.R;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    private static final long ONE_SECOND_IN_MILLIS = 1000L;

    public static String getDateFromMillis(Context context, long timeInMillis) {
        long milliseconds = timeInMillis * ONE_SECOND_IN_MILLIS;
        return DateFormat.getMediumDateFormat(context).format(new Date(milliseconds));
    }

    public static String getDaysDifference(Context context, long timeInMillis) {
        long nowMillis = System.currentTimeMillis();
        long diff = nowMillis - timeInMillis * ONE_SECOND_IN_MILLIS;
        return context.getString(R.string.task_duration_days, TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
    }

}
