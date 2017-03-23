package com.diucity.dingding.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.diucity.dingding.R;
import com.squareup.picasso.Picasso;
import com.yancy.gallerypick.inter.ImageLoader;
import com.yancy.gallerypick.widget.GalleryImageView;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class Picassoloader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, Context context, String path, GalleryImageView galleryImageView, int width, int height) {
        galleryImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(context)
                .load("file://" + path)
                .resize(width, height)
                .error(R.mipmap.gallery_pick_photo)
                .into(galleryImageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
