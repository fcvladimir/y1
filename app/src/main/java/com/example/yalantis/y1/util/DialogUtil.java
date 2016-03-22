package com.example.yalantis.y1.util;

import android.content.Context;
import android.widget.Toast;

public class DialogUtil {

    private static Toast mToast;

    /**
     * Method to show Toast-message with given text, duration short.
     * @param context context of the app.
     * @param message text need to be shown in Toast-message.
     */
    public static void show(Context context, String message) {
        if (message == null) {
            return;
        }
        if (mToast == null && context != null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        }
        if (mToast != null) {
            mToast.setText(message);
            mToast.show();
        }
    }

}
