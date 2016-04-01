package com.example.yalantis.y1;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initApplication();
    }

    private void initApplication() {

        initImageLoader();
    }

    /**
     * Initialize configuration of image loader library.
     */
    private void initImageLoader() {
        // Determine cache memory size
        int memoryCacheSize;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            int memClass = ((ActivityManager)
                    getSystemService(Context.ACTIVITY_SERVICE))
                    .getMemoryClass();
            memoryCacheSize = (memClass / 8) * 1024 * 1024; // 1/8 of app memory limit
        } else {
            memoryCacheSize = 2 * 1024 * 1024;
        }
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(5)
                /**
                 * Sets the priority for image loading threads. Should be <b>NOT</b> greater than {@link Thread#MAX_PRIORITY} or
                 * less than {@link Thread#MIN_PRIORITY}<br />
                 * Default value - {@link #DEFAULT_THREAD_PRIORITY this}
                 */
                .threadPriority(Thread.NORM_PRIORITY - 2) // default
                /**
                 * Sets maximum memory cache size for {@link android.graphics.Bitmap bitmaps} (in bytes).<br />
                 * Default value - 1/8 of available app memory.<br />
                 * <b>NOTE:</b> If you use this method then
                 * {@link com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache LruMemoryCache} will be used as
                 * memory cache. You can use {@link #memoryCache(MemoryCache)} method to set your own implementation of
                 * {@link MemoryCache}.
                 */
                .memoryCacheSize(memoryCacheSize)
                /**
                 * Limited {@link Bitmap bitmap} cache. Provides {@link Bitmap bitmaps} storing. Size of all stored bitmaps will not to
                 * exceed size limit. When cache reaches limit size then cache clearing is processed by FIFO principle.<br />
                 * <br />
                 * <b>NOTE:</b> This cache uses strong and weak references for stored Bitmaps. Strong references - for limited count of
                 * Bitmaps (depends on cache size), weak references - for all other cached Bitmaps.
                 */
                .memoryCache(new FIFOLimitedMemoryCache(memoryCacheSize-1000000))
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();

        ImageLoader.getInstance().init(config);
    }
}
