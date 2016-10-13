package com.hejunlin.tvsample;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.hejunlin.tvsample.widget.MetroViewBorderHandler;
import com.hejunlin.tvsample.widget.MetroViewBorderImpl;

public class HomeActivity extends Activity {

    private static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        FrameLayout roundedFrameLayout = new FrameLayout(this);
        roundedFrameLayout.setClipChildren(false);

        final MetroViewBorderImpl metroViewBorderImpl = new MetroViewBorderImpl(roundedFrameLayout);
        metroViewBorderImpl.setBackgroundResource(R.drawable.border_color);

        ViewGroup list = (ViewGroup) findViewById(R.id.list);
        metroViewBorderImpl.attachTo(list);

        metroViewBorderImpl.getViewBorder().addOnFocusChanged(new MetroViewBorderHandler.FocusListener() {
            @Override
            public void onFocusChanged(View oldFocus, final View newFocus) {
                metroViewBorderImpl.getView().setTag(newFocus);

            }
        });
        metroViewBorderImpl.getViewBorder().addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                View t = metroViewBorderImpl.getView().findViewWithTag("top");
                if (t != null) {
                    ((ViewGroup) t.getParent()).removeView(t);
                    View of = (View) metroViewBorderImpl.getView().getTag(metroViewBorderImpl.getView().getId());
                    ((ViewGroup) of).addView(t);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                View nf = (View) metroViewBorderImpl.getView().getTag();
                if (nf != null) {
                    View top = nf.findViewWithTag("top");
                    if (top != null) {
                        ((ViewGroup) top.getParent()).removeView(top);
                        ((ViewGroup) metroViewBorderImpl.getView()).addView(top);
                        metroViewBorderImpl.getView().setTag(metroViewBorderImpl.getView().getId(), nf);

                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }


}
