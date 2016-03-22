package com.example.yalantis.y1.util;

import com.example.yalantis.y1.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class ImageUtils {

    public static final DisplayImageOptions UIL_USER_PHOTO_DISPLAY_OPTIONS = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher)
            .showImageForEmptyUri(R.mipmap.ic_launcher)
            .showImageOnFail(R.mipmap.ic_launcher)
            .imageScaleType(ImageScaleType.EXACTLY)
            .cacheInMemory(true)
            .build();

}
