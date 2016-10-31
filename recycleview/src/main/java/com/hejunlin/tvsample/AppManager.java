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

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.google.gson.Gson;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by hejunlin on 2016/9/16.
 * blog: http://blog.csdn.net/hejjunlin
 */
public class AppManager extends Application {

    private static Context mContext;
    private static OkHttpClient mHttpClient;
    private static Gson mGson;
    private static final long SIZE_OF_HTTP_CACHE = 10 * 1024 * 1024;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mHttpClient = new OkHttpClient();
        mGson = new Gson();
        initHttpClient(mHttpClient, mContext);
    }

    private void initHttpClient(OkHttpClient client, Context context) {
        File httpCacheDirectory = context.getCacheDir();
        Cache httpResponseCache = new Cache(httpCacheDirectory, SIZE_OF_HTTP_CACHE);
//        try {
//            httpResponseCache =
//        } catch (IOException e) {
//            Log.e("Retrofit", "Could not create http cache", e);
//        }
//        client.setCache(httpResponseCache);
    }

    public static Resources getResource() {
        return mContext.getResources();
    }

    public static Context getContext() {
        return mContext;
    }

    public static OkHttpClient getHttpClient() {
        return mHttpClient;
    }

    public static Gson getGson() {
        return mGson;
    }

    public static String getIMEI() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            String IMEI_Number = telephonyManager.getDeviceId();
            if ((IMEI_Number != null) && (IMEI_Number.length() > 0)) {
                return IMEI_Number;
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    public static boolean checkPermission(Context paramContext, String paramString) {
        return paramContext.checkCallingOrSelfPermission(paramString) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isNetworkMobile() {
        ConnectivityManager conMan = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMan.getNetworkInfo(0) != null) {
            final NetworkInfo.State mobile = conMan.getNetworkInfo(0).getState();
            if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
                return true;
            else
                return false;
        } else
            return false;
    }

    public static boolean isNetworkWifi() {
        ConnectivityManager conMan = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMan.getNetworkInfo(1) != null) {
            final NetworkInfo.State wifi = conMan.getNetworkInfo(1).getState();
            if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING)
                return true;
            else
                return false;
        } else
            return false;
    }


    /**
     * 读取application 节点  meta-data 信息
     */
    public static String buildChannel() {
        try {
            ApplicationInfo appInfo = mContext.getPackageManager()
                    .getApplicationInfo(mContext.getPackageName(),
                            PackageManager.GET_META_DATA);
            String mTag = appInfo.metaData.getString("UMENG_CHANNEL");
            return mTag;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
