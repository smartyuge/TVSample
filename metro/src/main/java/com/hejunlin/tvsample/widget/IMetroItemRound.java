package com.hejunlin.tvsample.widget;

import android.graphics.Canvas;

/**
 * Created by hejunlin on 2016/10/13.
 */
public interface IMetroItemRound {

    void drawRadious(Canvas canvas);

    int getWidth();

    int getHeight();

    MetroItemRound getRoundImpl();

}
