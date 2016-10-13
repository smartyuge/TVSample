package com.hejunlin.tvsample.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by hejunlin on 2015/7/19.
 */
public class DrawingOrderRelativeLayout extends RelativeLayout {
    private int position = 0;

    public DrawingOrderRelativeLayout(Context context) {
        super(context);
    }

    public DrawingOrderRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setChildrenDrawingOrderEnabled(true);
    }

    public void setCurrentPosition(int pos) {
        this.position = pos;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
            View focused = findFocus();
            int pos = indexOfChild(focused);
            if (pos >= 0 && pos < getChildCount()) {
                setCurrentPosition(pos);
                postInvalidate();
            }

        return super.dispatchKeyEvent(event);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        View v = getFocusedChild();
        int pos = indexOfChild(v);
        if (pos >= 0 && pos < childCount)
            setCurrentPosition(pos);
        if (i == childCount - 1) {
            return position;
        }
        if (i == position) {
            return childCount - 1;
        }
        return i;
    }

}
