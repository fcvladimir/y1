package com.example.yalantis.y1;

import android.app.Application;

import com.example.yalantis.y1.manager.ContentManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class App extends Application {

    public static ContentManager contentManager;

    @Override
    public void onCreate() {
        super.onCreate();

        initApplication();
    }

    private void initApplication() {
        contentManager = new ContentManager(this);
        initImageLoader();
    }

    /**
     * Initialize configuration of image loader library (Default configuration).
     */
    private void initImageLoader() {
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
    }
}
