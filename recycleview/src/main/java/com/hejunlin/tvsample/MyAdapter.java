/*
 * Copyright (C) 2016 hejunlin <hejunlin2013@gmail.com>
 * github:https://github.com/hejunlin2013/TVSample
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
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
/**
 * Created by hejunlin on 2015/10/16.
 * blog: http://blog.csdn.net/hejjunlin
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    // 数据集
    private List<DetailInfo> mDataset;
    private Context mContext;
    private int id;
    private View.OnFocusChangeListener mOnFocusChangeListener;
    private OnBindListener onBindListener;
    private static final String TAG = MyAdapter.class.getSimpleName();

    public interface OnBindListener {
        void onBind(View view, int i);
    }

    public MyAdapter(Context context, List<DetailInfo> dataset) {
        super();
        mContext = context;
        mDataset = dataset;
    }

    public MyAdapter(Context context, List<DetailInfo> dataset, int id) {
        super();
        mContext = context;
        mDataset = dataset;
        this.id = id;
        Log.d(TAG, "mDataset " + mDataset.size());
    }

    public MyAdapter(Context context, List<DetailInfo> dataset, int id, View.OnFocusChangeListener onFocusChangeListener) {
        super();
        mContext = context;
        mDataset = dataset;
        this.id = id;
        this.mOnFocusChangeListener = onFocusChangeListener;
    }

    public void setOnBindListener(OnBindListener onBindListener) {
        this.onBindListener = onBindListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        int resId = R.layout.detail_list_item;
        if (this.id > 0) {
            resId = this.id;
        }
        View view = LayoutInflater.from(mContext).inflate(resId, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (mDataset.size() == 0) {
            Log.d(TAG, "mDataset has no data!");
            return;
        }
        viewHolder.mTextDesc.setText(mDataset.get(i).getInfotext());
        viewHolder.mTextTitle.setText(mDataset.get(i).getTitle());
        Point point = ImageUtils.getGridVerPosterSize(mContext, 4);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(point.x, point.y);
        viewHolder.mPostImg.setLayoutParams(params);
        if (mDataset.get(i).getPoster() != null) {
            ImageUtils.displayImage(viewHolder.mPostImg, mDataset.get(i).getPoster(), point.x, point.y);
        } else {
            viewHolder.mPostImg.setImageDrawable(AppManager.getResource().getDrawable(R.drawable.ic_defalut_ver));
        }
        viewHolder.itemView.setTag(i);
        viewHolder.itemView.setOnFocusChangeListener(mOnFocusChangeListener);
        if (onBindListener != null) {
            onBindListener.onBind(viewHolder.itemView, i);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextDesc;
        public ImageView mPostImg;
        public TextView mTextTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextDesc = (TextView) itemView.findViewById(R.id.tv_desc);
            mPostImg = (ImageView) itemView.findViewById(R.id.iv_image);
            mTextTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    public void setData(List<DetailInfo> data) {
        this.mDataset = data;
    }

}
