package com.hejunlin.tvsample.widget;

import android.view.View;

/**
 * Created by hejunlin on 2016/10/13.
 */
public interface IMetroViewBorder {

    void onFocusChanged(View target, View oldFocus, View newFocus);

    void onScrollChanged(View target, View attachView);

    void onLayout(View target, View attachView);

    void onTouchModeChanged(View target, View attachView, boolean isInTouchMode);

    void onAttach(View target, View attachView);

    void OnDetach(View targe, View view);

}
