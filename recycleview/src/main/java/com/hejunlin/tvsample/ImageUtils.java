/*
 * Copyright (C) 2016 hejunlin <hejunlin2013@gmail.com>
 * Github:https://github.com/hejunlin2013/TVSample
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hejunlin.tvsample;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
/**
 * Created by hejunlin on 2015/10/16.
 * blog: http://blog.csdn.net/hejjunlin
 */
public class ImageUtils {

    public static final float VER_POSTER_RATIO = 0.73f;
    public static final float HOR_POSTER_RATIO = 1.5f;

    public static void displayImage(ImageView view, String picUrl, int width, int height) {
        if (picUrl != null && !picUrl.isEmpty() && view != null && height > 0 && width > 0) {
            if (height > width)
                Picasso.with(view.getContext()).load(picUrl).error(R.drawable.ic_vip_movie).centerCrop().resize(width, height).into(view);
            else
                Picasso.with(view.getContext()).load(picUrl).error(R.drawable.ic_vip_movie).centerCrop().resize(width, height).into(view);
        }
    }

    public static void displayImage(ImageView view, String picUrl) {
        if (picUrl != null && !picUrl.isEmpty() && view != null)
            Picasso.with(view.getContext()).load(picUrl).into(view);
    }

    public static void fixHorPosterRatio(final ImageView image) {
        image.post(new Runnable() {
            @Override
            public void run() {
                int width = image.getWidth();
                int height = Math.round((float) width / HOR_POSTER_RATIO);
                image.getLayoutParams().height = height;
                image.setVisibility(View.VISIBLE);
            }
        });
    }


    public static void fixVerPosterRatio(final ImageView image) {
        image.post(new Runnable() {
            @Override
            public void run() {
                int width = image.getWidth();
                int height = Math.round((float) width / VER_POSTER_RATIO);
                image.getLayoutParams().height = height;
                image.setVisibility(View.VISIBLE);
            }
        });
    }

    public static Point getGridVerPosterSize(Context mContext, int columns) {
        int width = getScreenWidthPixels(mContext) / columns;
        width = width - 2 * mContext.getResources().getDimensionPixelSize(R.dimen.margin_small);
        int height = Math.round((float) width / VER_POSTER_RATIO);
        Point point = new Point();
        point.x = width;
        point.y = height;
        return point;
    }

    public static Point getGridHorPosterSize(Context mContext, int columns) {
        int width = getScreenWidthPixels(mContext) / columns;
        width = width - 2 * mContext.getResources().getDimensionPixelSize(R.dimen.margin_small);
        int height = Math.round((float) width / HOR_POSTER_RATIO);
        Point point = new Point();
        point.x = width;
        point.y = height;
        return point;
    }

    public static int getScreenWidthPixels(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        return width;
    }

    public static int getScreenHeightPixels(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        return height;
    }

    public static float convertPixelsToDp(float px) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return Math.round(dp);
    }

    public static float convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

}
