package com.hejunlin.tvsample.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

public class MetroViewBorderImpl<X extends View> implements ViewTreeObserver.OnGlobalFocusChangeListener, ViewTreeObserver.OnScrollChangedListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnTouchModeChangeListener {

    private static final String TAG = MetroViewBorderImpl.class.getSimpleName();

    private ViewGroup mViewGroup;
    private IMetroViewBorder mMetroViewBorder;

    private X mView;
    private View mLastView;

    public MetroViewBorderImpl(Context context) {
        this(context, null, 0);
    }

    public MetroViewBorderImpl(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MetroViewBorderImpl(Context context, AttributeSet attrs, int defStyleAttr) {
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mMetroViewBorder = new MetroViewBorderHandler();
        mView = (X) new View(context, attrs, defStyleAttr);
    }

    public MetroViewBorderImpl(X view) {
        this.mView = view;
        mMetroViewBorder = new MetroViewBorderHandler();
    }

    public MetroViewBorderImpl(X view, IMetroViewBorder border) {
        this.mView = view;
        mMetroViewBorder = border;
    }

    public MetroViewBorderImpl(Context context, int resId) {
        this((X) LayoutInflater.from(context).inflate(resId, null, false));
    }

    public X getView() {
        return mView;
    }


    public void setBackgroundResource(int resId) {
        if (mView != null)
            mView.setBackgroundResource(resId);
    }

    @Override
    public void onScrollChanged() {
        mMetroViewBorder.onScrollChanged(mView, mViewGroup);
    }

    @Override
    public void onGlobalLayout() {
        mMetroViewBorder.onLayout(mView, mViewGroup);
    }

    @Override
    public void onTouchModeChanged(boolean isInTouchMode) {
        mMetroViewBorder.onTouchModeChanged(mView, mViewGroup, isInTouchMode);
    }

    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        try {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                if (oldFocus == null && mLastView != null) {
                    oldFocus = mLastView;
                }
            }
            if (mMetroViewBorder != null)
                mMetroViewBorder.onFocusChanged(mView, oldFocus, newFocus);
            mLastView = newFocus;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public <T extends MetroViewBorderHandler> T getViewBorder() {
        return (T) mMetroViewBorder;
    }

    public void setBorder(IMetroViewBorder border) {
        this.mMetroViewBorder = border;
    }

    public void attachTo(ViewGroup viewGroup) {
        try {
            if (viewGroup == null) {
                if (mView.getContext() instanceof Activity) {
                    Activity activity = (Activity) mView.getContext();
                    viewGroup = (ViewGroup) activity.getWindow().getDecorView().getRootView();
                }
            }

            if (mViewGroup != viewGroup) {

                ViewTreeObserver viewTreeObserver = viewGroup.getViewTreeObserver();
                if (viewTreeObserver.isAlive() && mViewGroup == null) {
                    viewTreeObserver.addOnGlobalFocusChangeListener(this);
                    viewTreeObserver.addOnScrollChangedListener(this);
                    viewTreeObserver.addOnGlobalLayoutListener(this);
                    viewTreeObserver.addOnTouchModeChangeListener(this);
                }
                mViewGroup = viewGroup;
            }
            mMetroViewBorder.onAttach(mView, viewGroup);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void detach() {
        detachFrom(mViewGroup);
    }

    public void detachFrom(ViewGroup viewGroup) {
        try {
            if (viewGroup == mViewGroup) {
                ViewTreeObserver viewTreeObserver = mViewGroup.getViewTreeObserver();
                viewTreeObserver.removeOnGlobalFocusChangeListener(this);
                viewTreeObserver.removeOnScrollChangedListener(this);
                viewTreeObserver.removeOnGlobalLayoutListener(this);
                viewTreeObserver.removeOnTouchModeChangeListener(this);
                mMetroViewBorder.OnDetach(mView, viewGroup);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
