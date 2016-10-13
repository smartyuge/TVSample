package com.hejunlin.tvsample.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;


public class MetroItemFrameLayout extends FrameLayout implements IMetroItemRound {

    private MetroItemRound mMetroItemRound;

    public MetroItemFrameLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public MetroItemFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MetroItemFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        mMetroItemRound = new MetroItemRound(this, context, attrs, defStyle);
        setWillNotDraw(false);

    }

    @Override
    public void draw(Canvas canvas) {
        mMetroItemRound.draw(canvas);
    }

    @Override
    public MetroItemRound getRoundImpl() {
        return mMetroItemRound;
    }

    @Override
    public void drawRadious(Canvas canvas) {
        super.draw(canvas);
    }
}

